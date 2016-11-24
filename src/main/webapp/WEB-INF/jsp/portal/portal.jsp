<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>首页</title>
  <link href="${pageContext.request.contextPath}/skins/portal/css/base.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/skins/js/jquery-1.4.4.min.js"></script>
  

</head>
<script type="text/javascript">

path = "<%=request.getContextPath()%>";
var groupNum = 1;
	$(document).ready(
		function(){
			
			 menuready();
			 MessageLoading();
			 
			

		}

	);

	function menuready(){

		//菜单展示区域大小

		$("#menu-folder").mouseover(function(){	
			$(this).css("background-color","#37424f");
		});
		$("#menu-folder").mouseout(function(){
			$(this).css("background-color","#394555");
		});

		$("#menu-folder").click(function(){	
		
		
		});

		

		//产品与服务
		$("#menu-product").mouseover(function(){	
			$(this).css("background-color","#37424f");
		});
		$("#menu-product").mouseout(function(){
			$(this).css("background-color","#414d5c");
		});
		$("#menu-product").click(function(){	

			if($("#menu-product-two").is(":hidden")){
				$("#menu-product-two").show();
			}else{
				$("#menu-product-two").hide();
			}
		});

		//用户操作	
		$("#menu-users").mouseover(function(){	
			$(this).css("background-color","#37424f");
		});
		$("#menu-users").mouseout(function(){
			$(this).css("background-color","#414d5c");
		});
		$("#menu-users").click(function(){	
			if($("#menu-users-two").is(":hidden")){
				$("#menu-users-two").show();
			}else{
				$("#menu-users-two").hide();
			}
		
		});
			
		
		//退出
		$("#header-user").click(function(){	
			//点击退出按钮，跳转到登录界面
			window.location.href=path+"/login.action";
			
		});

	}
	
	
	
	
	



</script>
<body>
<div id="header" class="header">

	<div id="header-logo" class="header-logo">
		<span id="header-logo-img" class="header-logo-img"></span>
		<span id="header-logo-txt" class="header-logo-txt">云猫电竞</span>
	</div>
	<div id="header-concole" class="header-concole">控制台</div>
	<div id="header-phone" class="header-phone">手机版</div>
	<div id="header-user" class="header-user">退出</div>
	
</div>
<div class="menu-body">
	<div id="menu" class="menu">
		<div id="menu-folder" class="menu-folder"></div>
		<li id="menu-product" class="menu-one">产品服务</li>
		<div id="menu-product-two" class="menu-product-two">
			<li>产品众筹</li>
			<li>在线订座</li>
			<li>在线商城</li>
			<li>接口配置</li>
		</div>
		<li id="menu-users" class="menu-one">用户中心</li>
		<div id="menu-users-two" class="menu-users-two">
			<li>账号管理</li>
			<li>账号充值</li>
			<li>用户设置</li>
		</div>


	</div>
	<div id="body" class="body">
		建设中
	</div>
</div>
</body>
</html>