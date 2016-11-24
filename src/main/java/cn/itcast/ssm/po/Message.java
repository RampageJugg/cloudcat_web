package cn.itcast.ssm.po;

import java.util.Date;
/**
 * 
 * @author Administrator
 * 页面连续加载数据册数
 */
public class Message {
    private Integer id;

    private String username;

    private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

   
}