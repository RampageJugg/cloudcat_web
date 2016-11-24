package cn.itcast.ssm.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.ssm.po.Items;

@Controller
@RequestMapping("/json")
public class JsonWriteTest {
	
		@RequestMapping("/queryJson")
		public String queryJson(Model model){
			
			
	
			return "/json/jsonTest";
			
			
		}
		
		@RequestMapping("/fileUploadQuery")
		public String fileUploadQuery(Model model){
			
			
	
			return "/json/fileUploadQuery";
			
			
		}
		
		@RequestMapping("/fileUpload")
		public String fileUpload(Model model){
			
			
	
			return "/json/flieUpload";
			
			
		}
	
		//请求json串(商品信息)，输出json(商品信息)
		//@RequestBody将请求的商品信息的json串转成Items对象
		//@ResponseBody将Items转成json输出
		@RequestMapping("/requestJson")
		public @ResponseBody Items requestJson(HttpServletRequest req,HttpServletResponse rep,@RequestBody Items items){
			
	
		
			System.out.println("name:"+items.getName());
			System.out.println("price:"+items.getPrice());
			
			//@ResponseBody将items转成json输出
			return items;
		}
		
		//请求key/value，输出json
		@RequestMapping("/responseJson")
		public @ResponseBody Items responseJson(HttpServletRequest req,HttpServletResponse rep){
			
			String userName = req.getParameter("username");
			Items items = new Items();
			items.setName(userName);
			System.out.println("userName:"+userName);
			
			//@ResponseBody将itemsCustom转成json输出
			return items;
		}

	
	

}
