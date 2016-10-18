<%@ page language="java" pageEncoding="UTF-8"%>     
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<form:form method="POST" modelAttribute="user">     
    username: <form:input path="userName"/><br/>     
    password: <form:password path="userPassword"/><br/>     
    yourmail: <form:input path="userEmail"/><br/>     
    <input type="submit" value="更新用户信息"/>     
</form:form>