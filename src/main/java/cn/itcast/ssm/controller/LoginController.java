package cn.itcast.ssm.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.User;
import cn.itcast.ssm.service.LoginService;


@Controller
public class LoginController {
		
		private static Logger logger = Logger.getLogger(LoginController.class);
		@Autowired
		LoginService loginService;
		
		@SuppressWarnings("unused")
		@RequestMapping("/login")
		public String login(HttpSession session, String username, String password)
				throws Exception {
			
			logger.debug("进入登录方法--login!!");
			
			if(username==null || "".equals(username)){
				session.setAttribute("msg", "");
				return "/login/login";
			}
			if(password==null || "".equals(password)){
				session.setAttribute("msg", "");
				return "/login/login";
			}
			
			String msg = "";
			
			// -1 为登录不通过，1为登录通过
			int code = -1;
			
	
			
			//验证用户名是否存在
			//User userParam = new User(username,password);
			
			User user = loginService.getUser(username);
	
			//获取到用户名和密码
			if(user == null){
				msg = "用户名不存在，请重新输入!";
			}else{
				if(user.getPassword().equals(password)){
					code = 1;
				}else{
					msg = "用户名或密码错误，请重新输入!";		
				}

			}

			System.out.println("msg:"+msg);
			// 在session中保存用户身份信息
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("msg", msg);
			
			if(code == 1){
				return "redirect:portal.action";
			}else{
				return "/login/login";
				
			}
			// 重定向到商品列表页面
			
		}

		// 退出
		@RequestMapping("/logout")
		public String logout(HttpSession session) throws Exception {

			// 清除session
			session.invalidate();

			// 重定向到商品列表页面
			return "redirect:/login/login";
		}

}
