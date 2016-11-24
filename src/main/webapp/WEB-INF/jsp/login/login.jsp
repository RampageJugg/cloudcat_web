<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登陆</title>
<link rel='stylesheet' type='text/css' href="${pageContext.request.contextPath}/skins/css/main.css" />

</head>
<script type="text/javascript">
path = "<%=request.getContextPath()%>";
window.document.onkeydown = loginEnterKey;
function loginEnterKey(evt){
evt = (evt) ? evt : window.event;
if (evt.keyCode) {
	//按下回车进行登录
   if(evt.keyCode == 13 || evt.keyCode == 18){
	   document.form.action=path+"/login.action";
	   document.form.submit();
   }
}
}


</script>
<body>
<div class="back">
<div class="login-form">
	<div class="login-title">密码登陆</div>
	<div class="login-msg">${msg}</div>
	<form name="form" action="${pageContext.request.contextPath}/login.action" method="post">
		<div class="username">
			<span  class="input-lable">用户名：</span>
			<input class="text" type="text" name="username" value="${username}" placeholder="请输入用户名">
		</div>
		<div class="password">
			<span class="input-lable">密码：</span>
			<input type="password" name="password" value="${password}" placeholder="请输入密码">
		</div>
		<div class="signin"><input type="submit" value="登录" ></div>
		<div class="register">

			<a href="${pageContext.request.contextPath}/register.action">忘记密码</a>
			<a href="${pageContext.request.contextPath}/register/query.action">免费注册</a>
			
		</div>
		
		
	</form>
	
</div>
</div>
</body>
</html>


