package com.iteason.pojo;

import java.io.Serializable;
import java.util.List;

import javax.swing.event.CellEditorListener;

/**
 * 
 * @author 阿荣
 * @Description:搜索返回的结果对象
 * @date: 2018年8月24日 下午8:50:32
 */
public class SearchResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * images
	 * id
	 * title
	 * sell_point
	 * price
	 */
	private List<TbItemSolrResult> itemList;//查询结果集
	private Long totalCount;//总条数
	private Long totalPage;//总页数
	private Long currentRows;//每页显示条数
	private Long currentPage;//当前页
	
	
	public Long getCurrentRows() {
		return currentRows;
	}
	public void setCurrentRows(Long currentRows) {
		this.currentRows = currentRows;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public List<TbItemSolrResult> getItemList() {
		return itemList;
	}
	public void setItemList(List<TbItemSolrResult> itemList) {
		this.itemList = itemList;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalPage() {
		this.totalPage = (long) (Math.ceil(Double.parseDouble((this.totalCount).toString()))/this.currentRows);
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
