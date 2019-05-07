package com.lancslot.morn.utils.mybatis;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class PageEntity implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	private Integer pageNo; //目前是第几页
    private Integer pageSize; //每页大小
    @JSONField(serialize = false)
    private Map param; //传入的参数
    @JSONField(serialize = false)
    private String orderColumn;
    @JSONField(serialize = false)
    private String orderTurn = "ASC";

    public PageEntity(){
        init();
    }

    public void init(){
        this.pageNo = 1;
        this.pageSize = 20;
        param = new HashMap();
    }

    public String getOrderColumn() {
        return this.orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderTurn() {
        return this.orderTurn;
    }

    public void setOrderTurn(String orderTurn) {
        this.orderTurn = orderTurn;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map getParam() {
        return param;
    }

    public void setParam(Map param) {
        this.param = param;
    }
}
