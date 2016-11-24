package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.ItemsQueryVo;
import cn.itcast.ssm.po.User;

public interface ItemCustomService {
	
	List findItemsList(ItemsQueryVo itemsQueryVo);

	List<User> queryUserList();
}
