package com.taotao.portal.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String testPost(String username, String password) {
//		return TaotaoResult.ok();
		return "{username:" + username + "\tpassword:" + password + "}"; 
	}

}
