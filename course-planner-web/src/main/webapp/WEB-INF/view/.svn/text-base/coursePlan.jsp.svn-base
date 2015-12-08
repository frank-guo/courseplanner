<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Course Planner</title>
<script type="text/javascript" src="<spring:url value='static/jquery-1.11.0.min.js'/>"></script>
<script type="text/javascript"  src="<spring:url value='static/course-planner.js'/>"></script>
</head>
<body>
<p>Student Name: ${student.firstname} ${student.lastname}<br/>
   Student ID:  ${student.studentId}<br/>
</p>

<br/><br/>

<input type="button" value="Show My Course History" id="getCourseInfoBySemester"/><br/>
	<table border="1" style="width:300px" id="courseHistory">
	<tr>
  		<th></th>
  		<th>CourseID</th>
  		<th>Grade</th> 
	</tr>
	</table><br/><br/>

<input type="button" value="Show All My Current Course Plans" id="getAllCoursePlans"/><br/>
	<table border="1" style="width:300px" id="AllcoursePlans">
	<tr>
  		<th></th>
  		<th>courseId</th> 
  		<th>sectionId</th>
  		<th>semesterId</th>
  		<th>campusId</th>
	</tr>
	</table><br/><br/>

SemesterID you are currently planning for: <input type="text" name="semester" id="semesterId"/><br/>	
<input type="button" value="Show My Current Course Plans For This Semester" id="getCoursePlanBySemester"/><br/>
	<table border="1" style="width:300px" id="coursePlan">
	<tr>
  		<th></th>
  		<th>courseId</th> 
  		<th>sectionId</th>
  		<th>semesterId</th>
  		<th>campusId</th>
	</tr>
	</table>
<input type="button" value="Delete Selected Course" id="DeleteCrsSelection"/><br/><br/>	

<input type="button" value="Open Course Details For this semester" id="courseDetailsForSemester"/><br/><br/>	

<input type="button" value="Show All sections in this semester" id="getCousreOptionsBySemester"/><br/>
	<table border="1" style="width:300px" id="courseOptions">
	<tr>
  		<th></th>
  		<th>courseId</th>
  		<th>sectionId</th> 
  		<th>semesterId</th>
  		<th>campusId</th>
	</tr>
	</table>
<input type="button" value="Availble Courses for ${student.firstname}" id="getPlannableCourseOptionsBySemester"/><br/><br/>
	<table id="filter" border="1" style="width:500px" id="courseFiltered">
	<tr>
  		<th></th>
  		<th>courseId</th>
  		<th>sectionId</th> 
  		<th>semesterId</th>
  		<th>campusId</th>
	</tr>
	</table><input type="button" value="Create My Plan" id="createPlan"/><br/>
	
<input type="button" value="Submit Selected Course" id="submitCrsSelection"/><br/>



<div></div>	
	
<a href="<c:out value="${pageContext.request.contextPath}/studentlogout.html"/>">logout</a>
</body>
</html>