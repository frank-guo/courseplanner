$(document).ready(function(){
	  $('#btn1').click(function(){
	    alert ("test");
	  });
	  
	  $('#btn').click(function(){
	        var r1=$('input:radio[name=s]').val();
		    alert (r1);
		  });
	  
      var lol = $('#lolz');
      $('#lolb').click(function() {
          alert(lol.val());
      });
      
      $('#getCourseInfoBySemester').click(function() {
    	  $("#courseHistory tr").remove();
    	  $.getJSON("student/history",function(result){
    		  	var courseGrades = result;
		    	$("#courseHistory").append('<tr><th></th><th>CourseID</th><th>Grade</th></tr>');     		   			
    		    $.each(result.courseGrades, function(index, value){
    		    	 $("#courseHistory").append('<tr></tr>');
        		    $.each(value, function(key1, value1){
	        		     var   input = $('<input />').attr({
	        		            "type": "checkbox",
	        		            "class": "ckbox",
	        		            "value": value1,
	        		            "id": value1 
	        		     });
		        		if (key1=='courseId'){
		        			$("#courseHistory tr:last").append('<td></td>');
	        		    	$("#courseHistory tr:last td:last").append(input);
	        		    }
	        		    $("#courseHistory tr:last").append('<td></td>');
	        		    $("#courseHistory tr:last td:last").append(value1);
        		    }); 
        		    courseGrades = value;
    		    });  
  		    		    
    		    $("div").append("<br/>");
    		    $("div").append(JSON.stringify(courseGrades));
    		    $("div").append("<br/>"); 
    		  });
      });
 

      $('#getCoursePlanBySemester').click(function() {
          var semesterId = $("#semesterId").val();
          $("#coursePlan tr").remove();
    	  $.getJSON("student/plan/"+semesterId,function(result){
    		  	var selections = result;
		    	$("#coursePlan").append('<tr><th></th><th>courseId</th><th>sectionId</th><th>semesterId</th><th>campusId</th>');     		   			
    		    $.each(result.selections, function(index, selection){
    		    	 $("#coursePlan").append('<tr></tr>');
        		    $.each(selection, function(key, value){
 		        		if (key=='courseId'){
		        			$("#coursePlan tr:last").append('<td></td>');
	        		    	$("#coursePlan tr:last td:last").append('<input  type = "checkbox" class="'+value+ '"/>');
	        		    }
    		    	 	if (key == 'sectionId'){
    		    	 		$("#coursePlan tr td input:last").attr({"name" : value });
    		    	 	}
        		    	if (key == 'semesterId'){
        		    		$("#coursePlan tr td input:last").attr({"id" : value });	
    		    	 	}

	        		    $("#coursePlan tr:last").append('<td></td>');
	        		    $("#coursePlan tr:last td:last").append(value);
        		    }); 
        		    selections = selection;
    		    });  
  		    		    
    		    $("div").append("<br/>");
    		    $("div").append(JSON.stringify(selections));
    		    $("div").append("<br/>"); 
    		  });
      });
      
      $('#courseDetailsForSemester').click(function() {
    	  var semesterId = $("#semesterId").val();
    	  var url = 'course/semester/'+semesterId;
    	  window.open(url);
      });
      
      $('#DeleteCrsSelection').click(function() {
    	  var currentTerm = $("#semesterId").val();
    	  var courseplan ='{"studentId":1,"selections":[';
    	  var courseId='';
    	  $("#coursePlan input[type='checkbox']").each(function(index, checkbox){
        	  if ($(this).prop('checked')) {
        		  courseId = $(this).attr("class");
        		  sectionId = $(this).attr("name");
        		  semesterId = $(this).attr("id");
        		  selection = '{"courseId":"'+courseId+ '","sectionId":"' + sectionId +'","semesterId":'+ semesterId+',"campusId":null}'; 
            	  courseplan = courseplan + selection +',';
        	  }
    	  });
    	  courseplan = courseplan.substring(0,courseplan.length-1);
    	  courseplan =courseplan + ']}';
    	  $("div").append("<br/>");
		  $("div").append(courseplan);
		  $("div").append("<br/>");	
    	  
    	  $.ajax({
  		    type: 'POST',
  		    url: 'student/plan/delete/'+currentTerm,
  		    data: courseplan, 
  		    success: function(data) { alert('data: ' + data); },
  		    contentType: "application/json",
  		    dataType: 'json'
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
	  
	  var semesterId = $('#semesterId');
      $('#getPlannableCourseOptionsBySemester').click(function() {
    	  var URLString = "student/options/" + semesterId.val();
    	  $.getJSON(URLString,function(result){    		  
    		  var table = document.getElementById("filter");
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
  		      td.innerHTML= "CourseID".bold();
  		      td = tr.insertCell(2);
  		      td.innerHTML= "SectionID".bold();
  		      td = tr.insertCell(3);
  		      td.innerHTML= "SemesterID".bold();
  		      td = tr.insertCell(4);
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
    				  	td.innerHTML= value1['courseId'];
   
    				  	td = tr.insertCell(2);
        		  		td.innerHTML= value1['sectionId'];
        		    
        		  		td = tr.insertCell(3);
        		  		td.innerHTML= value1['semesterId'];  
     
        		  		td = tr.insertCell(4);
        		  		td.innerHTML= value1['campusId'];	          		    	
        		    }); 		         
  		      });        		  
  		  });
      });
	  
	  $('#createPlan').click(function() {
    	  var courseplan ='{"studentId":1,"selections":[';
    	  var courseId='';
    	  
    	  $("#filter tr").each(function () {
    		var checkbox = $(this).find("input[type=checkbox]");
      	    if(checkbox.prop('checked'))
      	    {
      	    	courseId = $(this).find("td:eq(1)").text().trim();
      	    	sectionId = $(this).find("td:eq(2)").text().trim();
      	    	semesterId = $(this).find("td:eq(3)").text().trim();
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
    		    success: function(data) { },
    		    contentType: "application/json",
    		    dataType: 'json'
    		});
    	});

});

function myFunction(s)
{
document.getElementById("lolz").value=s;
}