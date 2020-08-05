function register_board(){
	var form = $("#register_board_form");
	var formData = new FormData(form[0]);
	$.ajax({
	    url: form.attr('action'),
	    cache: false,
	    contentType: false,
	    processData: false,
	    data: formData,
	    type: form.attr('method'),                           
	    success: function(res){
	    	console.log(res);
	    	alert(res.msg);
	    	location.href="/main";
	    },
	    error: function(res){
	    	console.log(res.responseJSON);
	    	alert(res.responseJSON.msg);
	    }
	});
}