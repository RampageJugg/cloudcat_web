

	//IE7,8浏览器根据不同分辨率加载不同样式
	function adjustStyle(width) {
		var isIE = document.all ? 1 : 0;   //判断是否是IE浏览器
		//alert(isIE);
   		if (isIE == 1){ //是
	       if(navigator.userAgent.indexOf("MSIE7.0") > 0 
	       	  || navigator.userAgent.indexOf("MSIE 8.0") > 0){  

				$("#index-ie8").attr("href", path+"/css-ie8/index.css");
				$("#game-ie8").attr("href", path+"/css-ie8/game.css");
				$("#air-ie8").attr("href", path+"/css-ie8/air.css");
				$("#app-ie8").attr("href", path+"/css-ie8/app.css");

				width = parseInt(width);
			    if (width >= 1920 ) {
			    	//alert(1920);
			        $("#join-ie8").attr("href", path+"/css-ie8/join-1920.css");
			    } else if(width>=1366 && width<1920){
			    	//alert(1366);
			        $("#join-ie8").attr("href", path+"/css-ie8/join-1024.css"); 
			    }else if(width<1366){
			    	//alert(1024);
			    	$("#join-ie8").attr("href", path+"/css-ie8/join-1024.css");
			    }
	       		
	       }else{ //IE9以上

	       		$("#index").attr("href", path+"/css/index.css");
	       		$("#game").attr("href", path+"/css/game.css");
	       		$("#air").attr("href", path+"/css/air.css");
	       		$("#app").attr("href", path+"/css/app.css");
	       }
	    }else{ //其他浏览器

	    	$("#index").attr("href", path+"/css/index.css");
       		$("#game").attr("href", path+"/css/game.css");
       		$("#air").attr("href", path+"/css/air.css");
       		$("#app").attr("href", path+"/css/app.css");
	    }


	}
	

	
	$(document).ready(
		function(){
			 headReady();
			 adjustStyle($(this).width());
			 $(window).resize(function() {
			     adjustStyle($(this).width());
			 });
		}
	
	);


	//加盟页面
	function joinSelect(){
		//去掉背景图片
		$(".body").css("background-image","url()");
		//增加导航栏背景
		$(".header").css("background-color","#5c5c5c");


		//初始化选中精品店
		//隐藏按钮所有的下划线
		 $(".select-jingpin-line").hide(); 
		 $(".select-biaozhun-line").hide(); 
		 $(".select-qijian-line").hide(); 
		 
		 //字体颜色初始化
		 $(".select-jingpin-text").css("color","#999999");
		 $(".select-biaozhun-text").css("color","#999999");
		 $(".select-qijian-text").css("color","#999999");


		var clickTypeSelectId = "";
		 $(".select-jingpin-text").css("color","#fed300"); //按钮颜色变为橙色
		 $(".select-jingpin-line").show(); //显示下划线
		 clickTypeSelectId  =  $("#type-select-jingpin").attr("id"); //获取当前Id




		//投资模型模块，不同类型店添加事件
		$(".type-select-button").mouseover(function(){
		
			var selectId = $(this).attr("id");
			if(selectId==$("#type-select-jingpin").attr("id")){
				$(".select-jingpin-text").css("color","#fed300");
			}
			else if(selectId==$("#type-select-biaozhun").attr("id")){
				$(".select-biaozhun-text").css("color","#fed300");
			}
			else if(selectId==$("#type-select-qijian").attr("id")){
				$(".select-qijian-text").css("color","#fed300");
			}

			//鼠标形状变为手型
			$(this).css("cursor","pointer");

		});
		$(".type-select-button").mouseout(function(){

			var selectId = $(this).attr("id");
			if(selectId!=clickTypeSelectId){
				
				if(selectId==$("#type-select-jingpin").attr("id")){
					$(".select-jingpin-text").css("color","#999999");
				}
				else if(selectId==$("#type-select-biaozhun").attr("id")){
					$(".select-biaozhun-text").css("color","#999999");
				}
				else if(selectId==$("#type-select-qijian").attr("id")){
					$(".select-qijian-text").css("color","#999999");
				}
			}
			//鼠标形状变为箭头
			$(this).css("cursor","");
		});


		
		$(".type-select-button").click(function(){
			//如果有选中的按钮，则还原样式
			if(clickTypeSelectId!=""){
				//字体
				$(".select-jingpin-text").css("color","#999999");
				$(".select-biaozhun-text").css("color","#999999");
				$(".select-qijian-text").css("color","#999999");
				//下划线
				$(".select-jingpin-line").hide(); 
				$(".select-biaozhun-line").hide(); 
				$(".select-qijian-line").hide(); 
			}

			//给已选中id赋值
			clickTypeSelectId = $(this).attr("id");


			if(clickTypeSelectId==$("#type-select-jingpin").attr("id")){
				$(".select-jingpin-text").css("color","#fed300");
				$(".select-jingpin-line").show(); 
			}
			else if(clickTypeSelectId==$("#type-select-biaozhun").attr("id")){
				$(".select-biaozhun-text").css("color","#fed300");
				$(".select-biaozhun-line").show(); 
			}
			else if(clickTypeSelectId==$("#type-select-qijian").attr("id")){
				$(".select-qijian-text").css("color","#fed300");
				$(".select-qijian-line").show(); 

			}
			//填充数据
			modeldata(clickTypeSelectId);

		});

		//填充数据
		modeldata(clickTypeSelectId);


	}

	function modeldata(clickTypeSelectId){

		if(clickTypeSelectId==$("#type-select-jingpin").attr("id")){
				//alert("精品店数据！");
				$("#model-base-text-01").text("180平");
				$("#model-base-text-02").text("4平");
				$("#model-base-text-03").text("3.97元");
				$("#model-base-text-04").text("8元/小时");
				$("#model-base-text-05").text("45台");
				$("#model-base-text-06").text("80.64元");
				$("#model-base-text-07").text("42%");

				$("#model-in-text-01").text("155W");
				$("#model-in-text-02").text("");
				$("#model-in-text-03").text("8元");
				$("#model-in-text-04").text("127W");
				$("#model-in-text-05").text("18W");
				$("#model-in-text-06").text("10W");

				$("#model-crowd-text-01").text("109.9W");
				$("#model-crowd-text-02").text("");
				$("#model-crowd-text-03").text("29.7W");
				$("#model-crowd-text-04").text("27W");
				$("#model-crowd-text-05").text("6.75W");
				$("#model-crowd-text-06").text("0");
				$("#model-crowd-text-07").text("4.5W");
				$("#model-crowd-text-08").text("25W");
				$("#model-crowd-text-09").text("16.9W");


				$("#model-repay-text-01").text("735068/年");
				$("#model-repay-text-02").text("1.63W");
				$("#model-repay-text-03").text("1.51W");
				$("#model-repay-text-04").text("3+15个月");
				$("#model-repay-text-05").text("67%");
				$("#model-repay-text-06").text("46.29%");

				$("#model-cast-text-01").text("81.5W");
				$("#model-cast-text-02").text("");
				$("#model-cast-text-03").text("25W");
				$("#model-cast-text-04").text("0.45W");
				$("#model-cast-text-05").text("20W");
				$("#model-cast-text-06").text("6.8W");
				$("#model-cast-text-07").text("6.8W");
				$("#model-cast-text-08").text("23.25W");
				$("#model-cast-text-09").text("6W");
				

				
		}
		else if(clickTypeSelectId==$("#type-select-biaozhun").attr("id")){
				//alert("标准店数据！"); 
				$("#model-base-text-01").text("300平");
				$("#model-base-text-02").text("4平");
				$("#model-base-text-03").text("3.7元");
				$("#model-base-text-04").text("8元/小时");
				$("#model-base-text-05").text("75台");
				$("#model-base-text-06").text("74.88元");
				$("#model-base-text-07").text("39%");

				$("#model-in-text-01").text("2365600");
				$("#model-in-text-02").text("");
				$("#model-in-text-03").text("8元");
				$("#model-in-text-04").text("1965600");
				$("#model-in-text-05").text("30W");
				$("#model-in-text-06").text("10W");

				$("#model-crowd-text-01").text("175.2W");
				$("#model-crowd-text-02").text("");
				$("#model-crowd-text-03").text("49.5W");
				$("#model-crowd-text-04").text("45W");
				$("#model-crowd-text-05").text("11.25W");
				$("#model-crowd-text-06").text("0");
				$("#model-crowd-text-07").text("4.5W");
				$("#model-crowd-text-08").text("40W");
				$("#model-crowd-text-09").text("25W");

				$("#model-repay-text-01").text("102.6W/年");
				$("#model-repay-text-02").text("1.37sW");
				$("#model-repay-text-03").text("1.47W");
				$("#model-repay-text-04").text("3+17.5个月");
				$("#model-repay-text-05").text("59%");
				$("#model-repay-text-06").text("37.59%");

				$("#model-cast-text-01").text("133W");
				$("#model-cast-text-02").text("");
				$("#model-cast-text-03").text("40W");
				$("#model-cast-text-04").text("0.45W");
				$("#model-cast-text-05").text("40W");
				$("#model-cast-text-06").text("12W");
				$("#model-cast-text-07").text("35.5W");
				$("#model-cast-text-08").text("6W");
				
				
		}
		else if(clickTypeSelectId==$("#type-select-qijian").attr("id")){
				//alert("旗舰店数据！"); 

				$("#model-base-text-01").text("500平");
				$("#model-base-text-02").text("4平");
				$("#model-base-text-03").text("3.83元");
				$("#model-base-text-04").text("8元/小时");
				$("#model-base-text-05").text("125台");
				$("#model-base-text-06").text("69.12元");
				$("#model-base-text-07").text("36%");

				$("#model-in-text-01").text("362.4W");
				$("#model-in-text-02").text("");
				$("#model-in-text-03").text("8元");
				$("#model-in-text-04").text("302.4W");
				$("#model-in-text-05").text("50W");
				$("#model-in-text-06").text("10W");

				$("#model-crowd-text-01").text("284W");
				$("#model-crowd-text-02").text("");
				$("#model-crowd-text-03").text("82.5W");
				$("#model-crowd-text-04").text("75W");
				$("#model-crowd-text-05").text("18.75W");
				$("#model-crowd-text-06").text("0");
				$("#model-crowd-text-07").text("4.5W");
				$("#model-crowd-text-08").text("67W");
				$("#model-crowd-text-09").text("36W");

				$("#model-repay-text-01").text("149.6W/年");
				$("#model-repay-text-02").text("1.2W");
				$("#model-repay-text-03").text("1.446W");
				$("#model-repay-text-04").text("3+19.8个月");
				$("#model-repay-text-05").text("67%");
				$("#model-repay-text-06").text("46.29%");

				$("#model-cast-text-01").text("212.8W");
				$("#model-cast-text-02").text("");
				$("#model-cast-text-03").text("67W");
				$("#model-cast-text-04").text("0.45W");
				$("#model-cast-text-05").text("65W");
				$("#model-cast-text-06").text("20W");
				$("#model-cast-text-07").text("54.4W");
				$("#model-cast-text-08").text("6W");
			

		}

	}



	//app页面
	function appSelect(){
		//$(".body").css("background-image","url(path/img/app/xuhua.jpg");
		document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/app/xuhua.jpg')";
	}

	//云猫电竞页面
	function  gameSelect(){
		var clickPhotoId = "";
		
		//导航栏添加样式
		$(".header").css("background-color","#000000");  //添加背景色
		$(".header").css("opacity","0.50");  //不透明度30%
		
		
		//初始化第一张图片选中
		//$(".body").css("background-image","url(path/img/photo/1_sel.jpg");
		document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/photo/1_sel.jpg')";
	
		$("#game-photo-01").css("border","3px solid white");
		clickPhotoId = $("#game-photo-01").attr("id");
	

		$(".game-photo").mouseover(function(){
			
			//鼠标移入图片，边框变为白色
			$(this).css("border","3px solid white");
			
			//鼠标形状变为手型
			$(this).css("cursor","pointer");

		});
		$(".game-photo").mouseout(function(){
			if($(this).attr("id")!=clickPhotoId){
				//鼠标移出图片，边框变为无色
				$(this).css("border","");
			}
			//鼠标形状变为箭头
			$(this).css("cursor","");
		});


		
		$(".game-photo").click(function(){
			//如果已选中，则清除样式，再增加边框效果
			if(clickPhotoId!=""){
				$(".game-photo").css("border","");
				$(this).css("border","3px solid white");
			}

			clickPhotoId = $(this).attr("id");
			

			if($(this).attr("id")==$("#game-photo-01").attr("id")){
				//$(".body").css("background-image","url(./img/photo/1_sel.jpg");
				//$(".body").removeClass("body").addClass("body-01"); //替换样式

				document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/photo/1_sel.jpg')";


			}
			else if($(this).attr("id")==$("#game-photo-02").attr("id")){
				//$(".body").css("background-image","url(path/img/photo/2_sel.jpg");
				document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/photo/2_sel.jpg')";
			}
			else if($(this).attr("id")==$("#game-photo-03").attr("id")){
				//$(".body").css("background-image","url(path/img/photo/3_sel.jpg");
				document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/photo/3_sel.jpg')";
			}
			else if($(this).attr("id")==$("#game-photo-04").attr("id")){
				//$(".body").css("background-image","url(path/img/photo/4_sel.jpg");
				document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/photo/4_sel.jpg')";
			}
			else if($(this).attr("id")==$("#game-photo-05").attr("id")){
				//$(".body").css("background-image","url(path/img/photo/5_sel.jpg");
				document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/photo/5_sel.jpg')";
			}
			else if($(this).attr("id")==$("#game-photo-06").attr("id")){
				//$(".body").css("background-image","url(path/img/photo/6_sel.jpg");
				document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/photo/6_sel.jpg')";
			}
			

		});




	}






	
	var clickId = "";
	function headReady(){
	
		/*$("#cloud-cat-index").css("background-image","url(./img/select.png)");  //初始化选中首页
		$("#cloud-cat-index").css("color","#FFFFFF");
		clickId = $("#cloud-cat-index").attr("id");*/

		//隐藏其他页面
		$(".content-game").hide();
		$(".content-room").hide();
		$(".content-air").hide();
		$(".content-app").hide();
		$(".content-join").hide();

		$(".header-nav ul li").mouseover(function(){
			var mouseOutId = $(this).attr("id");
			if(mouseOutId!=clickId){
				$(this).css("color","#FFFFFF");
			}
			$(this).css("cursor","pointer");
		});
		$(".header-nav ul li").mouseout(function(){
			var mouseOutId = $(this).attr("id");
			if(mouseOutId!=clickId){
				$(this).css("color","#E0E0E0");
			}
			$(this).css("cursor","");
			
		});



		$(".header-nav ul li").click(function(){
			//初始化背景图
			//$(".body").css("background-image","url(./img/back.png");
			document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/back.png')";
			//初始化导航栏
			$(".header").css("background-color","");
			$(".header").css("opacity","1");  

			clickId = $(this).attr("id");
			$(".header-nav ul li").css("background-image","");
			$(".header-nav ul li").css("color","#E0E0E0");

			$(this).css("background-image","url('"+path+"/img/select.png')");
			$(this).css("color","#FFFFFF");


			$(".content-text").hide();
			$(".content-game").hide();
			$(".content-room").hide();
			$(".content-air").hide();
			$(".content-app").hide();
			$(".content-join").hide();
			if($(this).attr("id")==$("#cloud-cat-index").attr("id")){ //首页

				$(".content-text").show();

			}else if($(this).attr("id")==$("#cloud-cat-game").attr("id")){

				$(".content-game").show();
				gameSelect();

			}else if($(this).attr("id")==$("#cloud-cat-room").attr("id")){

			    $(".content-room").show();
			    gameSelect();

			}else if($(this).attr("id")==$("#cloud-cat-air").attr("id")){

				$(".content-air").show();
				gameSelect();

			}else if($(this).attr("id")==$("#cloud-cat-app").attr("id")){

				$(".content-app").show();
				appSelect();

			}else if($(this).attr("id")==$("#cloud-cat-join").attr("id")){

				$(".content-join").show();
				joinSelect();

			}
		});





		//logo添加事件

		$("#cloud-cat-logo").mouseover(function(){
			
			$(this).css("cursor","pointer");
		});
		$("#cloud-cat-logo").mouseout(function(){
			
			$(this).css("cursor","");
			
		});

		$("#cloud-cat-logo").click(function(){
			//清除选择框
			$(".header-nav ul li").css("background-image","");
			//初始化导航栏
			$(".header").css("background-color","");
			$(".header").css("opacity","1");  
			
			
			//初始化背景图
			//$(".body").css("background-image","url(./img/back.png");
			document.getElementById("whole-body").style.backgroundImage="url('"+path+"/img/back.png')";
			

			$(".content-text").hide();
			$(".content-game").hide();
			$(".content-room").hide();
			$(".content-air").hide();
			$(".content-app").hide();
			$(".content-join").hide();

			$(".content-text").show();

		});

	}




