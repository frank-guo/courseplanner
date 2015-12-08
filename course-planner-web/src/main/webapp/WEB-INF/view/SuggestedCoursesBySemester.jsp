<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="cmpt470.group7.project.domain.CoursePlanJdbc"%>
<%@page import="java.util.List"%>
<%@page import="cmpt470.group7.project.model.Courseplan"%>


<jsp:useBean id="list" type="java.util.List<CoursePlanJdbc>" scope="request" /> 

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
    <h1> Course suggestions for semester 1141</h1>
	<table border="1" style="width:300px">
	<tr>
  		<th>CourseID</th>
  		<th>Section ID</th>
    </tr>
		
	<% for (CoursePlanJdbc plan : list) {%>
		<tr>
	  	<td><%= plan.getCourseId()%></td> 
	  	<td><%= plan.getSectionId()%></td>
	</tr>
	<%}%>
	
	
	</table>	
     </body>
 </html>