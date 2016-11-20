/**
 * 异步数据交互实现
 * 
 */


/**
 * @author lingdong
 *
 */
package com.netease.course.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.course.dao.MybatisContentDao;
import com.netease.course.dao.MybatisPersonDao;
import com.netease.course.dao.MybatisProductDao;
import com.netease.course.dao.MybatisTrxDao;
import com.netease.course.dao.User;

@Controller
@RequestMapping(value="/api")
public class asynDataController {

	private HttpSession session;
	
	@Autowired
	private MybatisPersonDao personDao;
	
	@Autowired
	private MybatisContentDao contentDao;
	
	@Autowired
	private MybatisTrxDao trxDao;
	
	@Autowired
	private MybatisProductDao productDao;
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, 
			String message, Boolean result, Integer usertype, HttpServletRequest request, ModelMap map) throws IOException{
		int code;
		User user;
		session = request.getSession();
		if(password.equals(personDao.getPersonPassword(userName))){
			usertype = personDao.getUserType(userName);
			code = 200;
			result = true;
			user = new User(userName, usertype);
		}
		else {
			user = null;
			code = 400;
			message = "illegal login";
			result = false;
		}
		map.addAttribute("user", user);
		map.addAttribute("code", code);
		map.addAttribute("result", result);
		map.addAttribute("message", message);
		session.setAttribute("user", user);
		session.setAttribute("code", code);
		session.setAttribute("result", result);
		session.setAttribute("message", message);
		return "";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteProduct(@RequestParam("id") Number id, 
			String message, Boolean result, HttpServletRequest request, ModelMap map, Integer code) throws IOException{
		result = false;
		try {
			contentDao.deleteProduct(id);
			productDao.deleteProduct(id);
			result = true;
			code = 200;
		}catch(Exception es) {
			message = "illegal delete";
			code = 400;
		}
		map.addAttribute("code", code);
		map.addAttribute("result", result);
		map.addAttribute("message", message);
		session.setAttribute("code", code);
		session.setAttribute("result", result);
		session.setAttribute("message", message);
		return "";
	}
	

	@RequestMapping(value="/buy", method=RequestMethod.POST)
	public String buyProduct(@RequestParam("id") Integer id, 
			String message, Boolean result, HttpServletRequest request, ModelMap map, Integer code, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Integer personId = user.getUsertype();
		long time = System.currentTimeMillis();
		code = response.getStatus();
		try {
			Long buyTime = time;
			productDao.updateProduct(id,buyTime);
			double price = productDao.getBuyPrice(id);
			trxDao.buyProduct(id, personId, price * 100, time);
			message = "购买成功";
			result = true;
			code = 200;
		}
		catch(Exception es) {
			message = "购买失败";
			result = false;
			code = 400;
		}
		map.addAttribute("code", code);
		map.addAttribute("message", message);
		map.addAttribute("result", result);
		session.setAttribute("code", code);
		session.setAttribute("message", message);
		session.setAttribute("result", result);
		return "";
	}
}
