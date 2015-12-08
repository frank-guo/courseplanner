<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="cmpt470.group7.project.service.CourseService"%>
<%@page import="cmpt470.group7.project.model.Course"%>
<%@page import="java.util.List"%>
<%@page import="cmpt470.group7.project.model.Courseoption"%>
<%@page import="cmpt470.group7.project.model.CourseoptionId"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="courseService" type="cmpt470.group7.project.service.CourseService" scope="request" />
<%--<jsp:useBean id="course" type="cmpt470.group7.project.model.Course" scope="request" />--%>
<jsp:useBean id="list" type="java.util.List<Courseoption>" scope="request" /> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
    <title>Course Search Result</title>
            <script type="text/javascript" src="<c:url value='/static/jquery-1.11.0.min.js'/>"></script>
		<script type="text/javascript"  src="<c:url value='/static/allCourseInfo.js'/>"></script>
    <script>

    </script>

	<style>
		table,th,td
		{
		border:1px solid black;
		}
		h1{
			font-size: x-large;
		}	
	</style>
    </head>
 
    <body>
    <h1> Full Course Information For Semester</h1>
	<table border="1" style="width:300px">
	<tr>
  		<th>CourseID</th>
  		<th>Course Title</th>
  		<th class="courseDscp">Course Description</th>
  		<th>WQB</th> 
  		<th>sectionID</th>
  		<th>campusID</th>
  		<th>semesterID</th>
	</tr>
		
	<% for (Courseoption co : list) {%>
	<%CourseoptionId coId=co.getCourseoptionId();%>
	<%String courseId=coId.getCourseId();%>
	<tr>
	  	<td><%= courseId%></td> 
	 	<td><%= courseService.getCourse(courseId).getTitle() %></td>
	 	<td class="courseDscp"><%= courseService.getCourse(courseId).getDescription()%></td>
	 	<td><%= courseService.getCourse(courseId).getWqb()%></td>
	 	<td><%= co.getCourseoptionId().getSectionId()%></td>
	 	<td><%= co.getCampusId()%></td>
	 	<td><%= co.getCourseoptionId().getSemesterId()%></td>
	</tr>
	<%}%>	
	</table>
	
     </body>
 </html>