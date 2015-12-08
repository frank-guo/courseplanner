$(document).ready(function() {

	// bind enter key to #i_login
	$('#i_login').bind("keyup", function(event) {
		if (event.keyCode == '13') {
			event.preventDefault();
			doLogin();
		}
	});
	
	// bind enter key to #i_password
	$('#i_password').bind("keyup", function(event) {
		if (event.keyCode == '13') {
			event.preventDefault();
			doLogin();
		}
	});

	$('#i_submit').bind("click", function() {
		doLogin();
	});

	function doLogin() {
		if (!$('#i_login').val()) {
			$('#i_error').html('login Id is required<br/>');
			$('#i_error').show();
			return;
		}
		if (!$('#i_password').val()) {
			$('#i_error').html('password is required<br/>');
			$('#i_error').show();
			return;
		}
		var $user = $('#i_login').val();
		var $pw = $('#i_password').val();
		$.ajax({
			type : "POST",
			url : 'student/authenticate',
			dataType : 'json',
			contentType : 'application/json',
			async : false,
			data : '{"login":"' + $user + '", "password":"' + $pw + '"}',
			success : function(data) {
				if (data.success) {
					window.location = data.page;
				} else {
					$('#i_error').html('Your login attempt failed, please try again.<br/>');
					$('#i_error').show();
				}
			}
		});
	}

	$('#i_reset').bind("click", function() {
		$('#i_error').val('');
		$('#i_error').hide();
		$('#i_login').val('');
		$('#i_password').val('');
	});

});
