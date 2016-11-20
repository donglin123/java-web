package com.netease.course.dao;

import java.sql.Blob;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MybatisContentDao {
	
	@Result(property="image",column="icon")
	@Select("select * from content")
	public List<Content> getProductList();
	
	@Results(
			{@Result(property = "title", column = "title"),
			@Result(property = "image", column = "icon"),
			@Result(property = "detail", column = "text"),
			@Result(property = "price", column = "price"),
			@Result(property = "summary", column = "abstract")
		})
	@Select("select * from content where id = #{id}")
	public Content getProduct(int id);
	
	@Update("delete from content where id = #{id}")
	public void deleteProduct(Number id);
	
	@Results(
		{@Result(property = "title", column = "title"),
		@Result(property = "image", column = "icon"),
		@Result(property = "detail", column = "text"),
		@Result(property = "price", column = "price"),
		@Result(property = "summary", column = "abstract")
	})
	@Insert("insert into content (title, icon, text, price, abstract) values(#{title}, #{image}, #{detail}, #{price}, #{summary})")
	public void submit(@Param("title")String title, @Param("image")String image, @Param("detail")String detail, @Param("price")double price, @Param("summary")String summary);
	
	@Select("select id from content where title = #{title}")
	public Integer getContentId(String title);
	
	@Results(
			{@Result(property = "title", column = "title"),
			@Result(property = "image", column = "icon"),
			@Result(property = "detail", column = "text"),
			@Result(property = "price", column = "price"),
			@Result(property = "summary", column = "abstract")
		})
	@Update("update content set title=#{title}, abstract=#{summary}, text=#{detail}, icon=#{image}, price=${price} where id =#{id}")
	public void editContent(@Param("id")int id, @Param("title")String title,  @Param("summary")String summary, @Param("detail")String detail, @Param("image")String image, @Param("price")double price);
}
