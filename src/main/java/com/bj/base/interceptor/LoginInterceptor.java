package com.bj.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录认证拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	//执行完成handler
	//统一的异常处理，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse rep, Object ob, Exception ex)
			throws Exception {
		
		
		
		System.out.println("LoginInterceptor-afterCompletion");
		
	}

	//执行handler之后
	//ModelAndView，将公用的模型数据传到视图
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse rep,
			Object ob, ModelAndView mv) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("LoginInterceptor-postHandle");
	}

	
	//执行handler之前
	//身份认证，身份授权
	//如果用户认证没通过，则由拦截器进行拦截， 不往下执行
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep,
			Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("LoginInterceptor-preHandle");
		
		
	
		
		//获取验证方式
		
		
		//获取请求的url
		String url = req.getRequestURI();
		//获取session中的用户名，密码 判断是否登录
		HttpSession httpSession =req.getSession();
		String username = (String) httpSession.getAttribute("username");
		String password = (String) httpSession.getAttribute("password");
		
		System.out.println("url--"+url);
		System.out.println("username--"+username);
		System.out.println("password--"+password);
		
		
		if(url.indexOf("login.action")>=0){  //访问登录界面
			return true;	
		}
		
		if(username != null && password != null){  //已登录
		
			return true;
		}

	
		
		
		//执行到这里表示用户需要身份认证
		req.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp").forward(req, rep);
		
		return false;
	}

}
