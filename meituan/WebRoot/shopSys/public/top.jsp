<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Frame top</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<style type="text/css">

		body {
			margin: 0;
		}
		#Head_1 {
			background-color:black;
		    height: 64px;
		    margin: 0 auto;
		    width: 100%;
		}
		#Head_1_Logo {
			float: left;
		    left: 20px;
		    position: absolute;
		    top: 12px;
			color: #F1F9FE;
		    font-family: Arial Black,Arial;
		    font-size: 28px;
		}
		#Head_1_UserWelcome {
		    float: right;
		    color: #B3E1FF;
		    font-family: "宋体";
		    font-size: 12px;
		    height: 25px;
			padding-top: 11px;
			margin-right: 20px;
		}
		#Head_2 {
			background-color:pink;
		    height: 36px;
		    margin: 0;
		    width: 100%;
		}
		#back_forward{
			margin-left:35px;
		}
	</style>
</head>

<body>
 	
	<!-- 上部 -->
	<div id="Head_1">
		<!-- 标题 -->
		<div id="Head_1_Logo">
			<b style="font-family: '黑体'">美团商家后台管理</b> 
        </div>
		<!-- 欢迎用户的文字 -->
		<div id="Head_1_UserWelcome">
			<img border="0" width="13" height="14" src="${pageContext.request.contextPath }/shopSys/style/images/user.gif" /> 
			您好，<b>管理员</b>&nbsp;退出
		</div>
		
	</div>
	<!-- 下部 -->
    <div id="Head_2">
		
		<div id="back_forward" >
			<a href="javascript: window.parent.right.history.back();">
				<b>后退</b>
			</a>
			&nbsp;
			<a href="javascript: window.parent.right.history.forward();">
				<b>前进</b>		
			</a>
        </div>
        
	</div>
</body>
</html>