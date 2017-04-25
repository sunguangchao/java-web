package com.smart.dao;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11981 on 2017/4/23.
 */
public class Page implements Serializable{
    private static int DEFAULT_PAGE_SIZE = 20;
    private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数
    private long start; // 当前页第一条数据在List中的位置,从0开始
    private List data; // 当前页中存放的记录,类型一般为List
    private long totalCount; // 总记录数

    /**
     * 构造方法，只构造空页.
     */
    public Page(){
        this(0,0,DEFAULT_PAGE_SIZE,new ArrayList());
    }

    /**
     * 默认构造方法.
     *
     * @param start	 本页数据在数据库中的起始位置
     * @param totalSize 数据库中总记录条数
     * @param pageSize  本页容量
     * @param data	  本页包含的数据
     */
    public Page(long start, long totalSize, int pageSize, List data){
        this.start = start;
        this.totalCount = totalSize;
        this.pageSize = pageSize;
        this.data = data;
    }

    public long getTotalCount(){
        return this.totalCount;
    }

    public long getTotalPageCount(){
        if (totalCount % pageSize == 0)
            return totalCount/pageSize;
        else
            return totalCount/pageSize + 1;
    }

    public int getPageSize(){
        return pageSize;
    }

    public List getResult(){
        return data;
    }

    public long getCurrentPageNo(){
        return start/pageSize + 1;
    }

    public boolean isHasNextPage(){
        return this.getCurrentPageNo() < this.getTotalPageCount();
    }

    public boolean isHasPreviousPage(){
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
     *
     * @see #getStartOfPage(int,int)
     */
    protected static int getStartOfPage(int pageNo){
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }
    /**
     * 获取任一页第一条数据在数据集的位置.
     *
     * @param pageNo   从1开始的页号
     * @param pageSize 每页记录条数
     * @return 该页第一条数据
     */
    public static int getStartOfPage(int pageNo, int pageSize){
        return (pageNo - 1) * pageSize;
    }






}
