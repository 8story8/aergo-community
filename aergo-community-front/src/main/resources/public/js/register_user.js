function register_user(){
	var form = $("#register_user_form");
	$.ajax({
	    url: form.attr('action'),
	    data: form.serialize(),               
	    type: form.attr('method'),                           
	    success: function(res){
	    	console.log(res);
	    	alert(res.msg);
	    	location.href="/";
	    },
	    error: function(res){
	    	console.log(res.responseJSON);
	    	alert(res.responseJSON.msg);
	    }
	});
}