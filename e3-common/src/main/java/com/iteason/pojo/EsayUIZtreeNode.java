package com.iteason.pojo;

import java.io.Serializable;
/**
 * 
 * @author 阿荣
 * @Description:easyui的ztree节点对象
 * @date: 2018年8月11日 下午1:16:21
 */
public class EsayUIZtreeNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long id;	//
	private String text;//内容
	private String state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}