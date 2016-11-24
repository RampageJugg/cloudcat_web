package cn.itcast.ssm.dao;

import java.util.List;

import cn.itcast.ssm.po.Message;


public interface MessageMapper {
	
	List<Message> getMessageGroup(int start, int end);

}
