<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/skins/js/jquery-1.4.4.min.js"></script>
<link rel='stylesheet' type='text/css' href="${pageContext.request.contextPath}/skins/register/css/main.css" />


<script type="text/javascript">

$(document).ready(
		function(){
			$(".btn-register").click(function(){   //注册校验
		 		var regName =  $("input[name*=regName]").val(); //用户名
		 		var pwd =  $("input[name*=pwd]").val(); //设置密码
		 		var pwdRepeat =  $("input[name*=pwdRepeat]").val(); //确认密码
		 		var phone =  $("input[name*=phone]").val(); //确认密码

		 		if(regName==null || regName==""){
		 			alert("用户名不能为空");
		 			return false;
		 		}

		 		if(pwd==null || pwd==""){
		 			alert("设置密码不能为空");
		 			return false;
		 		}

		 		if(pwdRepeat==null || pwdRepeat==""){
		 			alert("确认密码不能为空");
		 			return false;
		 		}
		 		if(phone==null || phone==""){
		 			alert("手机号不能为空");
		 			return false;
		 		}

		 		if(pwd!=pwdRepeat){
		 			alert("设置密码和确认密码不一致，请重新输入");
		 			return false;
		 		}
		 		
 			});
		}
);
	


</script>


</head>
<body>
<div class="header">
	<div class="logo-con">
		<a href="" class="header-logo"></a>
		<div class="header-title">欢迎注册云猫电竞</div>
		<div class="have-account">已有账号<a href="${pageContext.request.contextPath}/login.action">请登录</a></div>
	</div>
</div>
<div class="container">
	<div class="main-clearfix">
		<div class="reg-form">
			<form action="${pageContext.request.contextPath}/register/save.action" method="post">
				
                <div class="form-item form-item-account" id="form-item-account">
                    <label>用　户　名:</label>
                    <input type="text" id="form-account" name="regName" class="field" autocomplete="off" maxlength="20"
                           placeholder="您的账户名和登录名" default='<i class="i-def"></i>支持中文、字母、数字、“-”“_”的组合，4-20个字符' />
                    <i class="i-status"></i>
                </div>
                <div class="input-tip">
                    <span></span>
                </div>
                <div class="form-item">
                    <label>设 置 密 码:</label>
                 
                    <input type="password" name="pwd" id="form-pwd" class="field" maxlength="20"
                           placeholder="建议至少使用两种字符组合" default="<i class=i-def></i>建议使用字母、数字和符号两种及以上的组合，6-20个字符" />
                    <i class="i-status"></i>
                </div>
                <div class="input-tip">
                    <span></span>
                </div>
                <div class="form-item">
                    <label>确 认 密 码:</label>
                   
                    <input type="password" name="pwdRepeat" id="form-equalTopwd" class="field" placeholder="请再次输入密码"
                           maxlength="20" default='<i class="i-def"></i>请再次输入密码' />
                    <i class="i-status"></i>
                </div>
                <div class="input-tip">
                    <span></span>
                </div>
               
                <div class="item-phone-wrap">
                        <div class="form-item form-item-phone">
                            <label class="select-country" id="select-country" country_id="0086">手机号:<a href="javascript:void(0) "class="arrow"></a></label>
                            <input type="text" id="form-phone" name="phone" class="field" placeholder="建议使用常用手机"
                                   autocomplete="off" maxlength="11" default='<i class="i-def"></i>完成验证后，可以使用该手机登录和找回密码' />
                            <i class="i-status"></i>
                        </div>
                        <div class="input-tip">
                        <span></span>
                    </div>
                   
                </div>
              
                <div class="form-agreen">
                    <div><input type="checkbox" name="agreen" checked=""/>我已阅读并同意<a href="javascript:;" id="protocol">《云猫用户注册协议》</a> </div>
                    <div class="input-tip">
                        <span></span>
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn-register">立即注册</button>
                </div>

			</form>

		</div>
		<div class="reg-other"></div>
	</div>

</div>
</body>
</html>