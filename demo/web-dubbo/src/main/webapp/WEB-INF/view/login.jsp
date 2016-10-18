<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<!-- Custom Theme files -->
<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!--Google Fonts--
<link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
-->
</head>
<body>
<div class="login">
	<h2>登陆</h2>
	<div class="login-top">
		<h1>登陆 FORM</h1>
		<form id="myform" action="login" method="post">
			<input type="text" name="username" value="User Id" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Id';}">
			<input type="password" name="password" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}">
	    </form>
	    <div class="forgot">
	    	<a href="#">forgot Password</a>
	    	<input type="submit" value="Login" onclick="submitForm();">
	    </div>
	</div>
	<div class="login-bottom">
		<h3>New User &nbsp;<a href="#">Register</a>&nbsp Here</h3>
	</div>
</div>	
<div class="copyright">
	<p>Copyright &copy; 2015.Company name All rights reserved.</p>
</div>

</body>
<script type="text/javascript">
	function submitForm() {
		document.getElementById('myform').submit();
	}
</script>

</html>