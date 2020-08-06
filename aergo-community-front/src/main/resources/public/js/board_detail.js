function deleteBoardById(id) {
	$.ajax({
		url : "/board/delete",
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
			console.log(res.responseJSON);
			alert(res.responseJSON.msg);
		}
	});
}

function download(id) {
	location.href="/board/download?id="+id;
}