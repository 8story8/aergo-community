function register_board() {
	var form = $("#register_board_form");
	var formData = new FormData(form[0]);
	formData.append("email", email);
	$.ajax({
		url : form.attr('action'),
		cache : false,
		contentType : false,
		processData : false,
		data : formData,
		type : form.attr('method'),
		success : function(res) {
			console.log(res);
			alert(res.msg);
			location.href = "/main";
		},
		error : function(res) {
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