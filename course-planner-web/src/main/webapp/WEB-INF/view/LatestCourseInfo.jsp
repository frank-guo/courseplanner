<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="cmpt470.group7.project.service.CourseService"%>
<%@page import="cmpt470.group7.project.model.Course"%>
<%@page import="java.util.List"%>
<%@page import="cmpt470.group7.project.model.Courseoption"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="courseService" type="cmpt470.group7.project.service.CourseService" scope="request" />
<jsp:useBean id="list" type="java.util.List<String>" scope="request" /> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
        <title>Course Search Result</title>
        <link href="<c:url value='/static/lastest-style-courseinfo.css' />" rel="stylesheet">
        <script type="text/javascript" src="<c:url value='/static/jquery-1.11.0.min.js'/>"></script>
		<script type="text/javascript"  src="<c:url value='/static/latest-course-info.js'/>"></script>
		<script>

		</script>
		<style>
		</style>
    </head>
     
    <body>
    <div class="table" id="table1">
    <span class="header"> Course Information </span><br/>
    <b id="semesterId" >Semester ${semesterId}</b><br/>
    <a href='<c:out value="../../courseoption/semester/${semesterId}"/>'> Go To Full Course Info List For This Semester</a>
    <p class="guid1" id="guid">Click on CourseID to show the section details For Specified Semester in Text Box</p>
	<table border="1" style="width:300px" id="courseDetails">
	<tr>
  		<th>CourseID</th>
  		<th>Course Title</th>
  		<th class="courseDscp">Course Description</th>
  		<th>WQB</th> 
  		<th>credit</th> 
	</tr>
		
	<% for (String c : list) {%>
	<tr>
	  	<td id="<%=c%>" onclick="showCourseDetails('<%=c%>','${semesterId}')"><u><%=c%></u></td>
	  	<td><%= courseService.getCourse(c).getTitle() %></td> 
	 	<td class="courseDscp"><%=courseService.getCourse(c).getDescription()%></td>
	 	<td><%= courseService.getCourse(c).getWqb()%></td>
	 	<td><%= courseService.getCourse(c).getCredit()%></td>
	</tr>
	<%}%>	
	</table>
	</div>
	
	<div class="table" id="table2">
	<br/><br/><br/><br/>
    <span class="header"> Course Sections For Individual Course</span>
    <p class="guid1" id="guid">Click on instructor name to show the rating </p>
	<table border="1" style="width:300px" id=fullInfoForIndividualCourse>
	<tr>
  		<th>CourseID</th>
  		<th>sectionID</th>
  		<th>semesterID</th>
  		<th>campusID</th>
  		<th>Instructor</th>
	</tr>
	</table>
	</div>
	
	<div id="searchCondition">
	<div class="left"><label>SemesterID:</label></div> 
	<div class="right"> <input type="text" class="condition" id="semesterIdTextBox"/> </div>
	<div class="left"><label>CourseID:</label></div> 
	<div class="right"><input type="text" class="condition" id="courseIdTextBox"/></div>
	<div class="left"><label>CampusID:</label></div> 
	<div class="right"><input type="text" class="condition" id="campusIdTextBox"/></div>
	<input type="button" value="Go" id="getCourseBySetCondition"/>	
	</div>
	
	
	<div id="instructor">
	<b>Instructor Rating</b>
	<table border="1" style="width:300px" id="instructorDetail">
	<tr>
  		<th>instructorId</th>
  		<th>name</th>
  		<th>rating</th>
  		<th>easiness</th>
  		<th>easiness</th>
  		<th>votes</th>
  		<th>hot</th> 		
	</tr>
	</table>	
	</div>
	
     </body>
 </html>