<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="cmpt470.group7.project.service.CourseService"%>
<%@page import="cmpt470.group7.project.model.Course"%>

<%-- <jsp:useBean id="courseService" type="cmpt470.group7.project.service.CourseService" scope="request" />--%>
<jsp:useBean id="course" type="cmpt470.group7.project.model.Course" scope="request" />
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
        <title>Course Search Result</title>

	<style>
		table,th,td
		{
		border:1px solid black;
		}
	</style>
    </head>
 
    <body>
    <h1> Course Information </h1>
	<table border="1" style="width:300px">
	<tr>
  		<th>CourseID</th>
  		<th>Course Title</th>
  		<th>Course Description</th>
  		<th>WQB</th> 
	</tr>
	<%-- for (Course course : courseService.getAllCourse()) { --%>
	<tr>
	  	<td><%= course.getCourseId() %></td>
	  	<td><%= course.getTitle() %></td> 
	 	<td><%=course.getDescription()%></td>
	 	<td><%= course.getWqb() %></td>
	</tr>
	<%-- } --%>
	</table>
	
	<a href="../courseNoDscp/cmpt470">Delete Description from Table</a>
     </body>
 </html>