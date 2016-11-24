<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json交互测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/skins/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
//请求json，输出是json
/* function requestJson(){
	
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/json/requestJson.action',
		contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'{"name":"手机","price":999}',
		success:function(jsonStr){//返回json结果
			var dataStr = eval(jsonStr);
			for(var i in dataStr){
				//alert(JSON.stringify({"name":"手机","price":999}));
				alert(dataStr[i]);
			}
			
		}
		
	});
	
	
}
//请求key/value，输出是json
function responseJson(){
	
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/potal/responseJson.action',
		//请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
		//contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'name=手机&price=999',
		success:function(data){//返回json结果
			alert(data.name);
		}
		
	});
	
} */
var groupNum = 1;

$(document).ready(
	function(){
		 MessageLoading();	
	}

);

var loadingOver = true;  //执行完成
$(window).scroll(function() {
	
  // When scroll at bottom, invoked getData() function.
  if ($(window).scrollTop() + $(window).height() == $(document).height()) { 
	  if(loadingOver==true){
		  
		  $(".loading").show();
		  
		  MessageLoading();    
	  }
	
  }
});

//测试多条信息，联系加载
function MessageLoading(){
	//alert(11);
	loadingOver = false; //正在执行
	$.ajax({
		type:'post',
		async: false,   //同步
		url:'${pageContext.request.contextPath}/message.action?groupNum='+groupNum,
		contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		/* 	data:"{groupNum:" + groupNum + "}", */
		success:
			function(jsonStr){//返回json结果
				
				setTimeout(function () { 
					for(var i in jsonStr){	
					 $("#result").append('<li id="">' +
							 jsonStr[i].id + ' - ' + '<strong>' +
							 jsonStr[i].username + '</strong>' + ' —?' + '<br><span class="page_message">' +
							 jsonStr[i].message + '</span></li>');
					}
					
					$(".loading").hide();
					
					groupNum++;
					
					loadingOver = true;
					
				},2000);
			}
	});
}

function scrllTop(){
	scrollTo(0,0);   //返回顶部
}
</script>

  <style type="text/css">
  	#result{
  		width: 500px;
  		margin-right: auto;
  		margin-left: auto;
  		padding-bottom:20px;
  	}
	#result ol{
		margin: 0px;
		padding: 0px;
	}
	#result li{
		width:100%;
		height:auto;
		margin-top: 20px;
		border-top: 1px dotted #E1FFFF;
		padding-top: 20px;
		
	}
	.page_message{
		display:block;
		word-space:normal;
	}
	.loading{
		width:auto;
		height:60px;
		background-color:#0e0e0e;
		display:none;
	}
	
	.scroll-top{
		position: fixed;
		left:500px;
		bottom: 20px;
		z-index: 100;
	
	}
  
  </style>
</head>
<body>
<!-- <input type="button" onclick="requestJson()" value="请求json，输出是json"/>
<input type="button" onclick="responseJson()" value="请求key/value，输出是json"/>
<input type="button" onclick="MessageJson()" value="请求Message测试数据"/> -->

<div id="result"></div>
<div class="loading"><img href="${pageContext.request.contextPath}/skins/portal/img/loading.png"></img></div>
<div class="scroll-top">
<input type="button" onclick="scrllTop()" value="回到顶部"/> -->
</div>
</body>
</html>