package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;

/**
 * 商品规格参数
 * @author 11981
 *
 */
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Override
	public String getItemParamByItemId(Long itemId) {
		// TODO Auto-generated method stub
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0) {
			return "";
		}
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		return null;
	}

}
