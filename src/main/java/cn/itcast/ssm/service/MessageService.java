package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.Message;

public interface MessageService {

	public List<Message> getMessageGroup(int start, int end);
	

}
