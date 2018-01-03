package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 页面跳转，打开首页
 * @author 11981
 * 这里的“index”由springmvc.xml中的视图解析器
 * 加上前缀和后缀，进行渲染
 *
 */
@Controller
public class PageController {
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	/**
	 * 展示其他页面
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page) {
		return page;
	}

}
