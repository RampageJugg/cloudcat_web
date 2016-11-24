package cn.itcast.ssm.po;

import java.util.Date;

public class User {
    private Integer id;

    private String username;
    
    private String password;

	private Date birthday;

    private String sex;

    private String address;
    
    private String mail;
    
    private String phone;
    
   

    public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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
        this.username = username == null ? null : username.trim();
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
    
    
    public String toString(){
    	String retStr = 
    			"id:"+this.id+
    			"username:"+this.username+
    			"password:"+this.password+
    			"birthday:"+this.birthday+
    			"sex:"+this.sex+
    			"address:"+this.address;
    	
		return retStr;
    
    }
}