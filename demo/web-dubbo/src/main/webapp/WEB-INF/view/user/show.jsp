<%@ page language="java" pageEncoding="UTF-8"%>   
<html>  
${user.userName}----${user.userPassword}----${user.userEmail}    
<br/>  
<img alt="" src="http://192.168.88.240${user.photo}">  
<br/>     
<br/>     
<a href="<%=request.getContextPath()%>/user/add">继续添加用户</a>  
</html> 