package cn.itcast.ssm.controller;

import java.util.List;

import cn.itcast.ssm.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.service.ItemCustomService;
import com.bj.base.exception.CustomException;

@Controller
@RequestMapping("/items")
public class ItemCustom {
	
	@Autowired
	ItemCustomService itemCustomService;
	
	//返回String
	@RequestMapping("/queryItems_string")
	public String queryItems(Model model) throws CustomException{
	
		//调用service查找数据库，查询商品列表，这里使用静态数据模拟
	
		System.out.print("ItemCustom开始-----------");
		List itemsList = itemCustomService.findItemsList(null);
		
		/*if(itemsList!=null){
			throw new CustomException("商品信息太多了！");
		}*/

		model.addAttribute("itemsList", itemsList);
		
		System.out.print("ItemCustom结束-----------");

		return "/items/itemsList";
		
		
	}
	  
	@RequestMapping("/queryItems_model")
	public ModelAndView queryItems(){
	
		//调用service查找数据库，查询商品列表，这里使用静态数据模拟
	
		System.out.print("ItemCustom开始-----------");
		List itemsList = itemCustomService.findItemsList(null);
		//List itemsList = null;
		//返回ModelAndView
		ModelAndView modelAndView =  new ModelAndView();
		//相当于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList); 	 	
		
		//指定视图
		modelAndView.setViewName("/items/itemsList");
		
		System.out.print("ItemCustom结束-----------");

		return modelAndView;
		
	}

	@RequestMapping("queryUsers")
	public String queryUsers(ModelMap model){
		System.out.print("进入queryUsers方法");
		List<User> userList = itemCustomService.queryUserList();
		model.put("userList", userList);
		return "users/userlist";
	}

}
