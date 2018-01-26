package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
/**
 * 商品管理service
 * @author 11981
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		TbItemExample example = new TbItemExample();
		//添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}


	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//获取记录总数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}


	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception{
		// TODO Auto-generated method stub
		Long itemId = IDUtils.getItemId();
		item.setId(itemId);
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		TaotaoResult result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			//添加规格参数
			throw new Exception(); 
		}
		result = insertItemParamItem(itemId, itemParam);
		if (result.getStatus() != 200) {
			//添加规格参数
			throw new Exception(); 
		}
		return TaotaoResult.ok();
	}


	/**
	 * 商品的修改，未完成
	 */

	public TaotaoResult updateItem(TbItem item) {
		// TODO Auto-generated method stub
//		itemMapper.updateByExample(record, example)
		return null;
	}
	
	
	
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	/**
	 * 添加规格参数
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}
	
}
