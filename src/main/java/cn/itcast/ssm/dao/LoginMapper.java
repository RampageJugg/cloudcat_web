package cn.itcast.ssm.dao;

import cn.itcast.ssm.po.User;


public interface LoginMapper {
	
	User getUser(String username);
	
}
