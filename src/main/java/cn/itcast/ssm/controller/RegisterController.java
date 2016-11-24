package cn.itcast.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.User;
import cn.itcast.ssm.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	//注入Service层
	@Autowired
	private RegisterService registerService;
	
	/**
	 * 注册页面初始化
	 */
	@RequestMapping("/query")
	public String query(){
		
		return "register/register";
		
	}
	

	/**
	 * 注册
	 */
	
	@RequestMapping("/save")
	public String save(HttpServletRequest req, HttpServletResponse rep){
		
		String username = req.getParameter("regName");
		String password = req.getParameter("pwd");
		String phone = req.getParameter("phone");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);

		registerService.saveUser(user);
		
		return "register/register_success";
		
		
	}

}
