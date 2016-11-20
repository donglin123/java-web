package com.netease.course.dao;



import org.apache.ibatis.annotations.Select;


public interface MybatisPersonDao {

	
	@Select("select password from person where userName=#{userName}")
	public String getPersonPassword(String userName);
	
	@Select("select userType from person where userName=#{userName}")
	public int getUserType(String userName);
}
