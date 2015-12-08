
function showCourseDetails(courseId, semesterId)
{
		if (semesterId==""){
			alert("Please specify the semesterID to show the section details");
			return;
		}
	  $.getJSON("../../courseoption/semester-course/"+semesterId+"/"+courseId,function(result){
		    $("p#courseDscp").remove();
		    $("b#courseDscp").remove();
		    $("#fullInfoForIndividualCourse tr").remove();	
	    	$("#fullInfoForIndividualCourse").append('<tr><th>CourseID</th><th>sectionID</th><th>semesterID</th><th>campusID</th></tr>');     	
		    $.each(result.courseOptions, function(index, value){
		    	$("#fullInfoForIndividualCourse").append('<tr></tr>');
	  		    $.each(value, function(key1, value1){
	      		    $("#fullInfoForIndividualCourse tr:last").append('<td></td>');
	      		    $("#fullInfoForIndividualCourse tr:last td:last").append(value1);
	  		    }); 
		    }); 
		    //$("#fullInfoForIndividualCourse").append('<p></p>').text($('#'+courseId).text());
		    $("#fullInfoForIndividualCourse").after('<b id="courseDscp"></b>');
		    $("b#courseDscp").after('<p id="courseDscp"></p>');
		    $("b#courseDscp").text("Cousre Desctiption:");
		    $("p#courseDscp").text($('#'+courseId).next().next().text());
		  });
}

$(document).ready(function(){
	$(".courseDscp").hide();
	$('#getCourseBySetCondition').click(function() {
		  var semesterId =$("input#semesterIdTextBox").val();
		  var courseId =$("input#courseIdTextBox").val();
		  var campusId =$("input#campusIdTextBox").val();
		  $('b#semesterId').text('Semester'+semesterId);
		  $("#courseDetails tr").remove();
		  //$.getJSON("../../course/semester/"+semesterId+"-"+courseId+".json",function(result)
		  $.getJSON("../../coursesearch/?"+"semesterId="+semesterId+"&courseId="+courseId+"&campusId="+campusId,function(result){
			  $("#courseDetails").append('<tr><th>CourseID</th><th>Course Title</th><th class="courseDscp">Course Description</th><th>WQB</th><th>credit</th></tr>');     		   			
			  $.each(result.courseInfos, function(key1, vlaue1){
				  $("#courseDetails").append('<tr></tr>');
				  $.each(vlaue1, function(key, value){
	        		if (key=='courseId'){
	        			var functionString = "showCourseDetails('"+value+"','"+semesterId+"')";
	        			$("#courseDetails tr:last").append('<td id="'+value+'" onclick="'+functionString+ '"></td>');
	        			$("#courseDetails tr:last td:last").append('<u>'+value+'</u>');
	    		    } 
	        		else{
	        			if (key=='description'){
	        				$("#courseDetails tr:last").append('<td class="courseDscp"></td>');
	        			}
	        			else {
	        				$("#courseDetails tr:last").append('<td></td>');
	        			}
					  $("#courseDetails tr:last td:last").append(value);
	        		}
				  }); 
			  });
			  $(".courseDscp").hide();
		  });
		
	});
	
	$("#courseId").dblclick(function() {
		  alert( "Handler for .dblclick() called." );
		});
	
	
	// Set our timer global and give a timeout for stop typing.
    var timer =0;
    var timeout = 750;

    
    // Just clearing anything the user has in the text field on load.
    $("#semesterIdTextBox").val("");
    
    // Watch for the user to type in the text field.
    $("#semesterIdTextBox").keyup(function()
    {
        var semesterId =$("input#semesterIdTextBox").val();
    	// Clear timer if it's set.
        if (typeof timer != undefined)
            clearTimeout(timer);
               
        // Set status to show we're done typing on a delay.
        timer = setTimeout(function()
        {      	
        	url = "../../courseoption/semester/"+semesterId;
            $("a").attr({"href" : url });
        }, timeout);
    });
	
	
});

