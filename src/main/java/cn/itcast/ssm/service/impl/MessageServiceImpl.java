package cn.itcast.ssm.service.impl;

import java.util.List;

import cn.itcast.ssm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.dao.MessageMapper;
import cn.itcast.ssm.po.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;

	public List<Message> getMessageGroup(int start, int end) {

		return messageMapper.getMessageGroup(start, end);
	}

}
