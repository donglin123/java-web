package com.netease.course.dao;

import java.io.UnsupportedEncodingException;

public class Product {

	private Integer id;
	private String title;
	private String summary;
	private String detail;
	private String image;
	private double price;
	private double buyPrice;
	private Boolean isBuy;
	private Boolean isSell;
	private Long buyTime;
	public Long getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Long buyTime) {
		this.buyTime = buyTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) throws UnsupportedEncodingException {
		this.detail = new String(detail.getBytes("iso-8859-1"), "UTF-8");
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price / 100.0;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getBuyPrice() {
		return buyPrice / 100.0;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Boolean getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(Boolean isBuy) {
		this.isBuy = isBuy;
	}
	public Boolean getIsSell() {
		return isSell;
	}
	public void setissell(Boolean isSell) {
		this.isSell = isSell;
	}
	
	
}
