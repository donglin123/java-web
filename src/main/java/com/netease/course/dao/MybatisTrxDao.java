package com.netease.course.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface MybatisTrxDao {

	
	@Insert("insert into trx (contentId, personId, price, time) values(#{contentId}, #{personId}, #{price}, #{time})")
	public void buyProduct(@Param("contentId")Integer contentId, @Param("personId")Integer personId, 
			@Param("price")double price, @Param("time")Long time);
}
