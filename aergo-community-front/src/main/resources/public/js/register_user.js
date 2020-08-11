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
	    	var response = res.responseJSON;
	    	if(response != undefined){
				var msg = response.msg;
				alert(msg);
			}else{
				alert("관리자에게 문의하세요.");
			}
	    }
	});
}