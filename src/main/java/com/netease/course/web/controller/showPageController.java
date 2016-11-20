/**
 * 页面展示
 * 
 */


/**
 * @author lingdong
 *
 */
package com.netease.course.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.course.dao.MybatisContentDao;
import com.netease.course.dao.MybatisProductDao;
import com.netease.course.dao.Product;
import com.netease.course.dao.SubmitProduct;

@Controller
public class showPageController {

	private HttpSession session;
	@Autowired
	private MybatisContentDao contentDao;
	
	@Autowired
	private MybatisProductDao productDao;
	//跳转至首页
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap map) throws IOException{
		//获取商品列表
		List<Product> productList = productDao.getProductList();
		session = request.getSession();
		session.setAttribute("productList", productList);
		map.addAttribute("productList", productList);
		return "index";
	}
	
	//跳转至用户登录页面
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() throws IOException{
		return "login";
	}
	
	//显示商品的详细信息
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String show(@RequestParam("id") int id, HttpServletRequest request, ModelMap map) throws IOException{
		session = request.getSession();
		//获取对应id的商品
		Product product = productDao.getProduct(id);
		System.out.println(product.getPrice());
		session.setAttribute("product", product);
		map.addAttribute("product", product);
		return "show";
	}
	
	//登出页面
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) throws IOException{
		session = request.getSession();
		//如果session不为空，则使其失效
		if(session != null) {
			session.invalidate();
		}
		return "login";
	}
	
	//新产品发布页面
	@RequestMapping(value="/public", method=RequestMethod.GET)
	public String pub(HttpServletRequest request) throws IOException{

		return "public";
	}
	
	//产品发布提交页面
	@RequestMapping(value="/publicSubmit", method=RequestMethod.POST)
	public String publicSubmit(@RequestParam("title")String title, @RequestParam("image")String image, 
			@RequestParam("detail")String detail, @RequestParam("price")double price, ModelMap map,
			@RequestParam("summary")String summary, HttpServletRequest request) {
		//往content表中插入新数据
		contentDao.submit(title, image, detail, price * 100, summary);
		//往product表中插入新数据
		productDao.submit(title, image, detail, price * 100, summary);
		Integer id = contentDao.getContentId(title);
		session = request.getSession();
		SubmitProduct product = new SubmitProduct(id, title, summary, detail, image, price);
		session.setAttribute("product", product);
		map.addAttribute("product", product);
		return "publicSubmit";
	}
	
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public String account(HttpServletRequest request, ModelMap map) throws SerialException, UnsupportedEncodingException, SQLException {
		List<Product> buyList = productDao.getAccount();
		session = request.getSession();
		session.setAttribute("buyList", buyList);
		map.addAttribute("buyList", buyList);
		return "account";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String edit(@RequestParam("id")int id, HttpServletRequest request, ModelMap map) {
		Product product = productDao.getProduct(id);
		session = request.getSession();
		session.setAttribute("product", product);
		map.addAttribute("product", product);
		return "edit";
	}
	
	@RequestMapping(value="/editSubmit", method=RequestMethod.POST)
	public String editSubmit(@RequestParam("title")String title, @RequestParam("id")int id, @RequestParam("image")String image, 
			@RequestParam("detail")String detail, @RequestParam("price")double price, @RequestParam("summary")String summary, 
			HttpServletRequest request, ModelMap map) {
		System.out.println(price);
		contentDao.editContent(id, title, summary, detail, image, price * 100);
		productDao.editProduct(id, title, summary, detail, image, price * 100);
		Product product = productDao.getProduct(id);
		session = request.getSession();
		session.setAttribute("product", product);
		map.addAttribute("product", product);
		return "editSubmit";
	}
}
