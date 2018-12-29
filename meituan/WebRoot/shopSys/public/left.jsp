<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Frame left</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<style type="text/css">
		html{
			height: 100%;
		}
		body{
			background-color:#D8EDFC;
			margin: 0;
			padding: 0;
		}
		ul{
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		    width: 150px;
		}
		li a {
		    display: block;
		    color: #000;
		    padding: 8px 16px;
		    text-decoration: none;
		}
		
		li a:hover {
		    background-color: #555;
		    color: white;
		}
	</style>
</head>
<body>
	<ul id="Menu">
	    <li>
            <div>
				<img src="${pageContext.request.contextPath }/shopSys/style/images/func20001.gif" class="Icon" /> 
				功能选择
			</div>
            <ul>
            	<li>
                    <a target="right" href="#">商家信息</a>
				</li>
                <li>
                	<a target="right" href="#">添加菜品</a>
				</li>
                <li>
                	<a target="right" href="#">查看订单</a>
				</li>
                <li>
                	<a target="right" href="#">辅助信息</a>
				</li>
            </ul>
        </li>
    </ul>
</body>
</html>