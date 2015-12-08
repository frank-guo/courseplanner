<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Course Planner</title>
<link href="<c:url value="static/style.css" />" rel="stylesheet">
<script type="text/javascript" src="<spring:url value='static/jquery-1.11.0.min.js'/>"></script>
<script type="text/javascript"  src="<spring:url value='static/course-planner_Prod.js'/>"></script>
</head>
<body>

<p> ${student.firstname} ${student.lastname}
<a href="<c:out value="${pageContext.request.contextPath}/studentlogout.html"/>">
<input class="logout" type="button" value="Logout"/></a>
<input class="search" type="button" value="More Searches..." id="courseDetailsForSemester"/><br/>
Student ID:  ${student.studentId}<br/>
</p>

<div class="container">
	<div class="one">
	 <p id="lblAvailable"> Available courses for you in <input type="text" size=10 disabled id="semesterId"/><br>
	 Available at <select name="campus" id="campus"> 
     <option selected> All Campuses </option> 
     <option> Burnaby </option> 
     <option> Surrey   </option> 
     <option> Vancouver</option>   
     </select> 
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <input type="button" value="Create a plan" id="createPlan"/></p>
	<table style="width:330px" class="available" id="available" border="1">
	<tr>
  		<th></th>
  		<th>Course Number</th>
  		<th>Section</th> 
  		<th>Semester</th>
  		<th>Campus</th>
	</tr>
	</table>
	
	</div>
	<div class="two">
	<br>
	<p id="lblHistory"> Course History<p/>
	<table class="history" border="1"  id="courseHistory">
	<tr>  		
  		<th>Course Number</th>
  		<th>Credit</th> 
	</tr>
	</table>  	
   </div>    
	<div class="three"><br>
	    <p id="lblAllPlans"> Course Plan 
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <input type="button" value="Delete the last plan" id="deleteLastCoursePlan"/><p/>
	   	<table class = "plan" border="1" id="AllcoursePlans">
		<tr>
	  		<th>Course Number</th> 
	  		<th>Section</th>
	  		<th>Semester</th>
		</tr>
		</table>
	</div>        
</div>	
</body>
</html>