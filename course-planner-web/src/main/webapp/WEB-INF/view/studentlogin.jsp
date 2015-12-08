<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Course Planner Login Page</title>
<script type="text/javascript"
	src="<c:out value='${pageContext.request.contextPath}/static/jquery-1.11.0.min.js'/>"></script>
<script type="text/javascript"
	src="<c:out value='${pageContext.request.contextPath}/static/studentlogin.js'/>"></script>
<style>

.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 1px solid #ff0000;
	padding: 4px;
	margin: 0px;
	display: none;
	width: 270px;
}

body {
	color: #404040;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 13px;
	font-weight: normal;
	line-height: 18px;
}

.logon {
	background-color: #F5F5F5;
	border: 1px solid rgba(0, 0, 0, 0.05);
	border-radius: 4px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05) inset;
	margin-bottom: 20px;
	min-height: 20px;
	padding: 19px;
	width: 290px;
}

label {
	display: block;
	float: none;
	line-height: 20px;
	padding-top: 0;
	text-align: left;
	width: auto;
}

input {
	width: 270px !important;
	vertical-align: baseline;
}

.button-login {
	background-color: #AA272F;
	background-image: -moz-linear-gradient(center top, #CD202C, #AA272F);
	background-repeat: repeat-x;
	border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
	color: #FFFFFF;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	align: right;
}

.button-reset {
	background-color: #FF9900;
	background-image: -moz-linear-gradient(center top, #CD202C, #AA272F);
	background-repeat: repeat-x;
	border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
	color: #FFFFFF;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	align: right;
}
</style>
</head>
<body>
	<h2>Course Planner Student Login</h2>

	<div id="logon" class="logon">
		<form id='f' autocomplete="off">
			<table>
				<tr>
					<td><span id="i_error" class="errorblock"></span></td>
				</tr>
				<tr>
					<td><label>Login:</label></td>
				</tr>
				<tr>
					<td><input id="i_login" type='text' name='login' value=''
						autofocus="autofocus" /></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
				</tr>
				<tr>
					<td><input id="i_password" type='password' name='password' /></td>
				</tr>
				<tr>
					<td><input class="button-login" id="i_submit" type="button"
						value="Login" /></td>
				</tr>
				<tr>
					<td><input class="button-reset" id="i_reset" value="Reset"
						type="button" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>