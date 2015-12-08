function convertToSemesterId(str)
{
	if(str == "Summer 2014")
		return 1144;
    else if(str == "Fall 2014")
		return 1147;
	else if(str == "Spring 2015")
		return 1151;
	else if(str == "Summer 2015")
		return 1154;
	else if(str == "Fall 2015")
		return 1157;
	else if(str == "Spring 2016")
		return 1161;
	else if(str == "Summer 2016")
		return 1164;
	else if(str == "Fall 2016")
		return 1167;
	else 
		return 1144;
}

function convertToSemesterName(id)
{
	if(id == 1144)
		return "Summer 2014";
    else if(id == 1147)
		return "Fall 2014";
	else if(id == 1151)
		return "Spring 2015";
	else if(id == 1154)
		return "Summer 2015";
	else if(id == 1157)
		return "Fall 2015";
	else if(id == 1161)
		return "Spring 2016";
	else if(id == 1164)
		return "Summer 2016";
	else if(id == 1167)
		return "Fall 2016";
	else 
		return "Summer 2014";
}

function showCourseDetails(link, courseId)
{	  $.getJSON("course/"+courseId ,function(result){
		  var title = "";
		  $.each(result, function(index, value){    			 
			  $.each(value, function(key1, value1){   
				  title += value1["title"];
			  });				  
		  });   	
	      
	      link.title = title;	      
   		   
	});	  
	 
}

$(document).ready(function(){         
	
	// Gets the initial semesterId by referring planned courses
    $.ajax({
		type : "GET",
		url : 'student/semester',
		success : function(result) {	
			$('#semesterId').val(result);
			  // and then show the available courses for the next term.
		   	  var URLString = "student/options/" + convertToSemesterId(result);
		      $.getJSON(URLString,function(result){    		  
		    	  var table = document.getElementById("available");
		    	  var rowCount = table.rows.length;    	 
		    	  for(var i=0; i<rowCount; i++) {
			            var row = table.rows[i];
			            table.deleteRow(i);
			            rowCount--;
			            i--;
			      }    	     
		    	  
				  var tr = table.insertRow(-1);
				  var td = tr.insertCell(0);    		  
				  td = tr.insertCell(1);
			      td.innerHTML= "Course Number".bold();
			      td = tr.insertCell(2);
			      td.innerHTML= "Section".bold();
			      td = tr.insertCell(3);
			      td.innerHTML= "Campus".bold();
				  $.each(result, function(index, value){    			 
					  $.each(value, function(key1, value1){    				  
						  	var tr = table.insertRow(-1);						  	
						  	var newInput = document.createElement("INPUT");
						  	newInput.id = "mycheckboxid";
						  	newInput.name = "mycheckboxname";
						  	newInput.type = "checkbox";
						  	td = tr.insertCell(0);
						  	td.appendChild(newInput);

						  	td = tr.insertCell(1);
						  	td.innerHTML = '<a href="#" onmouseover="showCourseDetails(this,\''+value1["courseId"]+'\')">' + value1['courseId'] +'</a>';
								  			   
		    				td = tr.insertCell(2);
		        		  	td.innerHTML= value1['sectionId'];
		    		    		    		  		
		    		  		td = tr.insertCell(3);
		    		  		td.innerHTML= value1['campusId'];	          		    	
		    		    }); 		         
			      });        		  
			  });
		}
	});
       
    
    $("#courseHistory").hide();
    $("#lblHistory").hide();
    
    $.getJSON("student/history",function(result){
    	var table = document.getElementById("courseHistory");
   		$.each(result, function(index, value){    			 
   			$.each(value, function(key1, value1){  
   				 $("#courseHistory").show();  
   		   		 $("#lblHistory").show();
				  var tr = table.insertRow(-1);			  	
				  td = tr.insertCell(0);
				  td.innerHTML = '<a href="#" onmouseover="showCourseDetails(this,\''+value1["courseId"]+'\')">' + value1['courseId'] +'</a>';
						  			   
				  td = tr.insertCell(1);
				  td.innerHTML= value1['grade'];		    		    		  		
				        		    	
   			}); 		         
	     });        		  
	  });     
    
    $("#AllcoursePlans").hide();
    $("#lblAllPlans").hide();
    $("#deleteLastCoursePlan").hide();
   	$.getJSON("student/allplans",function(result){
   		var table = document.getElementById("AllcoursePlans");
   		$("#AllcoursePlans").show();  
   		$("#lblAllPlans").show();
   		$("#deleteLastCoursePlan").show();
   		
   		$.each(result, function(index, value){    			 
   			$.each(value, function(key1, value1){    				  
				  var tr = table.insertRow(-1);			  	
				  td = tr.insertCell(0);
				  td.innerHTML = '<a href="#" onmouseover="showCourseDetails(this,\''+value1["courseId"]+'\')">' + value1['courseId'] +'</a>';
						  			   
				  td = tr.insertCell(1);
				  td.innerHTML= value1['sectionId'];
 		    		    		  		
				  td = tr.insertCell(2);
				  td.innerHTML= convertToSemesterName(value1['semesterId']);	          		    	
   			}); 		         
	     });        		  
	  });
        
      $('#getCousreOptionsBySemester').click(function() {
    	  var semesterId = $("#semesterId").val();
    	  $("#courseOptions tr").remove();
    	  $.getJSON("courseoption/semester/"+semesterId+".json",function(result){
		    	$("#courseOptions").append('<tr><th><th>CourseID</th><th>SectionID</th><th>SemesterID</th><th>CampusID</th>');     		   			
    		    $.each(result.courseOptions, function(key1, courseOption){
        		    $("div").append("<br/>");
        		    $("div").append(JSON.stringify(courseOption));
        		    $("div").append("<br/>"); 
    		    	 $("#courseOptions").append('<tr></tr>');
    		    	 $.each(courseOption, function(key, value){
    		    		// var input = $('<input />');
 		        		if (key=='courseId'){
		        			$("#courseOptions tr:last").append('<td></td>');
	        		    	$("#courseOptions tr:last td:last").append('<input  type = "checkbox" class="'+value+ '"/>');
	        		    }
    		    	 	if (key == 'sectionId'){
    		    	 		$("#courseOptions tr td input:last").attr({"name" : value });
    		    	 	}
        		    	if (key == 'semesterId'){
        		    		$("#courseOptions tr td input:last").attr({"id" : value });	
    		    	 	}

	        		    $("#courseOptions tr:last").append('<td></td>');
	        		    $("#courseOptions tr:last td:last").append(value);
    		    	 }); 
    		    });  
    		});
      });
      
      $('#campus').change(function() {  
    	  var semesterId = $("#semesterId").val(); 
    	  var campusName = $('#campus :selected').text().trim();
    	  var campusId = campusName=="Burnaby"?"BRNBY":(campusName=="Surrey"?"SURRY":(campusName=="Vancouver"?"VANCR":""))
  		  var URLString = "student/options/" + convertToSemesterId(semesterId) + "/" + campusId;
  		  $.getJSON(URLString,function(result){    		  
  		      var table = document.getElementById("available");
  		      var rowCount = table.rows.length;    	 
  		      for(var i=0; i<rowCount; i++) {
  			        var row = table.rows[i];
  			        table.deleteRow(i);
  			        rowCount--;
  			        i--;
  			  }    	       		    	  
  			  var tr = table.insertRow(-1);
  			  var td = tr.insertCell(0);    		  
  			  td = tr.insertCell(1);
  			  td.innerHTML= "Course Number".bold();
  			  td = tr.insertCell(2);
  			  td.innerHTML= "Section".bold();
  			  td = tr.insertCell(3);
  			  td.innerHTML= "Campus".bold();
  			  $.each(result, function(index, value){    			 
  				  $.each(value, function(key1, value1){
  					var tr = table.insertRow(-1);						  	
				  	var newInput = document.createElement("INPUT");
				  	newInput.id = "mycheckboxid";
				  	newInput.name = "mycheckboxname";
				  	newInput.type = "checkbox";
				  	td = tr.insertCell(0);
				  	td.appendChild(newInput);

  					td = tr.insertCell(1);
  					td.innerHTML = '<a href="#" onmouseover="showCourseDetails(this,\''+value1["courseId"]+'\')">' + value1['courseId'] +'</a>';
  								  			   
  		    		td = tr.insertCell(2);
  		        	td.innerHTML= value1['sectionId'];
  		    		    		    		  		
  		    		td = tr.insertCell(3);
  		    		td.innerHTML= value1['campusId'];	          		    	
  		    	}); 		         
  			 });        		  
  		});
  	
  	});
         
	     	  	  
	  $('#createPlan').click(function() {
    	  var courseplan ='{"studentId":1,"selections":[';
    	  var courseId='';
    	  
    	  $("#available tr").each(function () {
    		var checkbox = $(this).find("input[type=checkbox]");
      	    if(checkbox.prop('checked'))
      	    {
      	    	courseId = $(this).find("td:eq(1)").text().trim();
      	    	sectionId = $(this).find("td:eq(2)").text().trim();
      	    	var semesterId = $('#semesterId').val();
      	    	semesterId = convertToSemesterId(semesterId);
      	    	selection = '{"courseId":"'+courseId+ '","sectionId":"' + sectionId +'","semesterId":'+ semesterId + '}'; 
            	courseplan = courseplan + selection +',';
        	 }
      	    
    	  });
    	  courseplan = courseplan.substring(0,courseplan.length-1);
    	  courseplan =courseplan + ']}';
    	  
    	  $.ajax({
    		    type: 'POST',
    		    url: 'student/plan',
    		    data: courseplan, 
    		    success: function(data) { 
    		    	location.reload();
    		    },
    		    contentType: "application/json",
    		    dataType: 'json'
    		});
    	});
	  
	    $('#deleteLastCoursePlan').click(function() {    	     	  
    	  $.ajax({
    		    type: 'DELETE',
    		    url: 'student/plan',
    		    success: function(data) { 
    		    	location.reload();
    		    }    	   
    		});
    	});
	    
	    $('#courseDetailsForSemester').click(function() {
	    	  var semesterId = $("#semesterId").val();
	    	  var url = 'course/semester/'+convertToSemesterId(semesterId);
	    	  window.open(url);
	    });

});

