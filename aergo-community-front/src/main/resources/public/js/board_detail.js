function deleteBoardById(id) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		url : "/board/delete",
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data : {
			id : id,
			email : email
		},
		type : "post",
		success : function(res) {
			console.log(res);
			alert(res.msg);
			location.href = "/main";
		},
		error : function(res) {
			var response = res.responseJSON;
			if (response != undefined) {
				var msg = response.msg;
				alert(msg);
			} else {
				alert("관리자에게 문의하세요.");
			}
		}
	});
}

function download(id) {
	location.href = "/board/download?id=" + id;
}