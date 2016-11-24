package cn.itcast.ssm.service.impl;

import java.util.List;

import cn.itcast.ssm.po.User;
import cn.itcast.ssm.service.ItemCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import cn.itcast.ssm.dao.ItemCustomMapper;
import cn.itcast.ssm.po.ItemsQueryVo;
import org.springframework.stereotype.Service;

@Service
public class ItemCustomServiceImpl implements ItemCustomService {
	
	
	@Autowired
	private ItemCustomMapper itemCustomMapper;

	public List findItemsList(ItemsQueryVo itemsQueryVo) {
		
		return itemCustomMapper.findItemsList(itemsQueryVo);
	}

	public List<User> queryUserList() {
		return itemCustomMapper.queryUserList();
	}

}
