function update_board() {
	var form = $("#update_board_form");
	var formData = new FormData(form[0]);
	formData.append("email", email);
	formData.append("id", id);
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
			location.href = "/board/"+id;
		},
		error : function(res) {
			console.log(res.responseJSON);
			alert(res.responseJSON.msg);
		}
	});
}