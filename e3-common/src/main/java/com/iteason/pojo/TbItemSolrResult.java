package com.iteason.pojo;

import java.io.Serializable;

/**
 * 
 * @author 阿荣
 * @Description:solr 查询所需的字段 存入索引库
 * @date: 2018年8月23日 下午9:11:04
 */
public class TbItemSolrResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String image;
	private String category_name;
	private String[] images;
	
	//images数组
	public String[] getImages(){
		String[] strings = this.image.split(",");
		return strings;
	}
	
	public void setImages(String[] images){
		this.images = images;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	
	
	
}
