    <%@ page language="java" pageEncoding="UTF-8"%>     
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
    <c:forEach items="${users}" var="user">     
        ${user.userName}----${user.userPassword}----${user.userEmail}      
            <a href="<%=request.getContextPath()%>/user/${user.userName}">查看</a>     
            <a href="<%=request.getContextPath()%>/user/${user.userName}/update">编辑</a>     
            <a href="<%=request.getContextPath()%>/user/${user.userName}/delete">删除</a>     
        <br/>     
    </c:forEach>     
    <br/>     
    <a href="<%=request.getContextPath()%>/user/add">继续添加用户</a>   
    <a href="<%=request.getContextPath()%>/out">退出</a>