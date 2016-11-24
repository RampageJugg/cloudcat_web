package com.bj.enu;

/**
 * 枚举类，存放登录请求的验证方式
 * @author Administrator
 *
 */
public enum AuthorityType{
	
	// 登录和权限都验证 默认
    Validate,
    
    // 不验证
    NoValidate,

    // 不验证权限
    NoAuthority;
    
  
}
