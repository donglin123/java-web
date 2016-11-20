package com.netease.course.dao;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.netease.course.dao.Product;

public interface MybatisProductDao {

	@Update("delete from product where id = #{id}")
	public void deleteProduct(Number id);
	
	@Select("select * from product")
	public List<Product> getProductList();
	
	@Select("select * from product where id =#{id}")
	public Product getProduct(Integer id);
	
	@Results(
			{@Result(property = "title", column = "title"),
			@Result(property = "image", column = "image"),
			@Result(property = "detail", column = "detail"),
			@Result(property = "price", column = "price"),
			@Result(property = "summary", column = "summary")
		})
	@Insert("insert into product (title, image, detail, price, summary) values(#{title}, #{image}, #{detail}, #{price}, #{summary})")
	public void submit(@Param("title")String title, @Param("image")String image, @Param("detail")String detail, @Param("price")double price, @Param("summary")String summary);
	
	@Select("select * from product where isBuy = 1")
	public List<Product> getAccount();
	
	@Select("select buyPrice from product where id =#{id}")
	public Integer getBuyPrice(Integer id);
	
	@Update("update product set isBuy=1, isSell=1, buyTime=#{buyTime}, buyPrice=price-1000 where id =#{id}")
	public void updateProduct(@Param("id")Integer id, @Param("buyTime")Long buyTime);
	
	@Update("update product set title=#{title}, summary=#{summary}, detail=#{detail}, image=#{image}, price=${price} where id =#{id}")
	public void editProduct(@Param("id")int id, @Param("title")String title,  @Param("summary")String summary, @Param("detail")String detail, @Param("image")String image, @Param("price")double price);

}
