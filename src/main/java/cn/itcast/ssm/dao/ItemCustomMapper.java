package cn.itcast.ssm.dao;

import java.util.List;

import cn.itcast.ssm.po.ItemsQueryVo;
import cn.itcast.ssm.po.User;

public interface ItemCustomMapper {
	
	List findItemsList(ItemsQueryVo itemsQueryVo);

    List<User> queryUserList();
}
