package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.dao.LoginMapper;
import cn.itcast.ssm.po.User;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;

	public User getUser(String  username) {
		// TODO Auto-generated method stub

		return loginMapper.getUser(username);
	}


}
