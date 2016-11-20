package com.netease.course.dao;


public class SubmitProduct {
	private Integer id;
	private String title;
	private String summary;
	private String detail;
	private String image;
	private double price;
	public SubmitProduct(Integer id, String title, String summary, String detail, String image, double price) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.detail = detail;
		this.image = image;
		this.price = price;
	}
}
