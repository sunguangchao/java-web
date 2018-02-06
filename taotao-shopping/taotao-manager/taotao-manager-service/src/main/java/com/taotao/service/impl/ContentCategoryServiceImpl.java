package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for(TbContentCategory content : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(content.getId());
			node.setText(content.getName());
			node.setState(content.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		// TODO Auto-generated method stub
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		contentCategoryMapper.insert(contentCategory);
		//查看父节点的isParent列是否为true,如果不是true改为true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return TaotaoResult.ok(contentCategory);
	}

	/**
	 * 删除节点
	 * 未完成
	 */
	@Override
	public TaotaoResult deleteContentCategory(long parentId, long id) {
		System.out.println("1");
		//先根据ID删除该节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		contentCategoryMapper.deleteByExample(example);
		
//		//查询该parentId下是否有子节点
//		TbContentCategoryExample example2 = new TbContentCategoryExample();
//		Criteria criteria2 = example2.createCriteria();
//		criteria2.andParentIdEqualTo(parentId);
////		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example2);
//		int count = contentCategoryMapper.countByExample(example2);
//		System.out.println("count:" + count);
//		//如果该parentId下没有子节点，则将isParent置为false
//		if (count == 0) {
//			TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
//			parentCat.setIsParent(false);
//			contentCategoryMapper.updateByPrimaryKey(parentCat);
//		}
		return TaotaoResult.ok();
	}

}
