package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	

	@Override
	public TaotaoResult checkData(String content, Integer type) {
		//设置查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if (1 == type) {//判断用户名是否可用
			criteria.andUsernameEqualTo(content);
		}else if (2 == type) {
			criteria.andPhoneEqualTo(content);
		}else {
			criteria.andEmailEqualTo(content);
		}
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult createUser(TbUser user) {
		//非空校验
		if (StringUtils.isBlank(user.getUsername())) {
			return TaotaoResult.build(400, "用户名不能为空");
		}
		
		TaotaoResult taotaoResult = checkData(user.getUsername(), 1);
		if (!(boolean)taotaoResult.getData()) {
			return TaotaoResult.build(400, "用户名重复");
		}
		//判断密码是否为空
		if (StringUtils.isBlank(user.getPassword())) {
			return TaotaoResult.build(400, "密码不能为空");
		}
		
		if (StringUtils.isNotBlank(user.getPhone())) {
			taotaoResult = checkData(user.getPhone(), 2);
			if (!(boolean)taotaoResult.getData()) {
				return TaotaoResult.build(400, "电话号码重复");
			}
		}
		
		if (StringUtils.isNotBlank(user.getEmail())) {
			taotaoResult = checkData(user.getEmail(), 3);
			if (!(boolean)taotaoResult.getData()) {
				return TaotaoResult.build(400, "Email重复");
			}
		}
		user.setUpdated(new Date());
		user.setCreated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		//返回注册成功
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		//如果没有此用户
		if (list == null || list.size() == 0) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		//密码要经过md5加密再校验
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		//生成token
		String token = UUID.randomUUID().toString();
		//保存用户之前，把用户对象中的密码清空
		user.setPassword(null);
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		//设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY+ ":" + token, SSO_SESSION_EXPIRE);
		//添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		//返回token
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		//根据Token从redis中查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		//非空校验
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "此session已过期，请重新登录");
		}
		//更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		//返回用户信息
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

	/**
	 * 根据token安全退出
	 */
	@Override
	public TaotaoResult logoutByToken(String token) {
		long result = jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
		if (result > 0) {
			return TaotaoResult.ok();
		}else {
			return TaotaoResult.build(400, "安全退出失败");
		}
	}

}
