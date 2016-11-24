package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.dao.RegisterMapper;
import cn.itcast.ssm.po.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterMapper registerMapper;

	public int saveUser(User  user) {

		return registerMapper.saveUser(user);
	}

}
