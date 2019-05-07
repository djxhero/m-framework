package com.lancslot.morn.utils.mybatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class PagingResult<T> implements Serializable{
	 
	private static final long serialVersionUID = 1L;
    //当前页
    public int currentPage;
    //总共记录条数
    public long totalSize;
    //总共页数
    public int totalPage;
    //结果集
    public List<T> resultList = new ArrayList<T>();

    public PagingResult(){}

    public PagingResult(int currentPage, long totalSize, int totalPage, List<T> resultList){
        this.currentPage = currentPage;
        this.totalSize = totalSize;
        this.totalPage = totalPage;
        this.resultList = resultList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

	@Override
	public String toString() {
		return "PagingResult [currentPage=" + currentPage + ", totalSize=" + totalSize + ", totalPage=" + totalPage
				+ ", resultList=" + resultList + "]";
	}

}
