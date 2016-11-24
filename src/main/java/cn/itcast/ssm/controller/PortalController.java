package cn.itcast.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.ssm.po.Message;
import cn.itcast.ssm.service.MessageService;

@Controller
public class PortalController {
	
	@Autowired
	MessageService messageService;
	
	

	@RequestMapping("/portal")
	public String portal(HttpServletRequest req,HttpServletResponse rep){
		
		
		return "/portal/portal";
		
	}
	
	/**
	 * 测试页面加载
	 * @param req
	 * @param rep
	 * @return
	 */
	@RequestMapping("/message")
	public @ResponseBody List<Message> getMessage(HttpServletRequest req,HttpServletResponse rep){
		//获取显示的组数,设定一组数据的区间为10
		
		
		String groupNum = req.getParameter("groupNum");
		System.out.println("groupNum:"+groupNum);
		//通过groupNum计算获取数据的区间
		int end  = Integer.valueOf(groupNum)*15;
		int start = end-14;
	
		System.out.println("start:"+start);
		System.out.println("end:"+end);
		List<Message> list = messageService.getMessageGroup(start, end); //获取区间内的消息
		
		
		//获取数据后，groupNum+1
		int groupNumNew = Integer.valueOf(groupNum)+1;
		req.setAttribute(groupNum,groupNumNew);
	
		return list;
		
	}
	

}
