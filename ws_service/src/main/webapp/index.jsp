<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <script type="text/javascript">
    
    	function reqWebService(){
    		// 1.创建请求对象实例
    		request = getHttpRequest();
    		// 2.打开http请求
    		request.open("post","http://localhost:8080/ws_service/ws_service/BookService_WS");
    		// 3.添加请求头信息
    		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    		// 4.装配请求报文soap-message
    		data="<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'><soap:Header><crident><username>tom</username><password>cat</password></crident></soap:Header><soap:Body><ns2:getMap xmlns:ns2='http://service.ws.atguigu.com/'/></soap:Body></soap:Envelope>";
    		// 5.发送请求
    		request.send(data);
    		// 6.监控请求状态,解析返回数据
    		 if (request.readyState==4 && request.status==200){
    			 var result = request.responseXML;
    			 var returnElem = result.getElementByTagName("return");
    			 alert(returnElem);
    			 console.info(returnElem);
    		 }
    	};
    	
		//声明创建http请求方法
		function getHttpRequest(){
			var xmlhttp = null;
			if (window.XMLHttpRequest){
				// code for IE7+, Firefox, Chrome, Opera, Safari
			  	xmlhttp=new XMLHttpRequest();
			  }else{
				try{// code for IE4
					xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){// code for IE6, IE5
			  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			 	 };
			}	
			return xmlhttp;
		};	
		
    </script>
</head>
  
  <body>
   	<a id="trigger" href="hello">发生ajax请求ws</a><br>
  </body>
</html>
