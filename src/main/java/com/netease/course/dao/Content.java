/**
 * Content类
 * 
 */


/**
 * @author lingdong
 *
 */
package com.netease.course.dao;
import java.io.UnsupportedEncodingException;

public class Content {

	private Integer id;
	private double price;
	private String title;
	private String icon;
	private String abs;
	private String text;
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return (price / 100.0);
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAbs() {
		return abs;
	}
	public void setAbs(String abs) {
		this.abs = abs;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) throws UnsupportedEncodingException {
		this.text = new String(text.getBytes("iso-8859-1"), "UTF-8");
	}
}

