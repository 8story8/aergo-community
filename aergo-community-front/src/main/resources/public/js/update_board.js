function download(id) {
	location.href = "/board/download?id=" + id;
}

function delete_file() {
	if (confirm($("#fileName").val() + "을 삭제하시겠습니까?")) {
		$("#fileName").hide();
		$("#btnDeleteFile").hide();
		$("#file").css('display', 'block');
		$("#uploadStatus").val("Delete");
	}
}

function setUploadStatus(file) {
	if (file.files[0] === undefined) {
		$("#uploadStatus").val("Delete");
	} else {
		$("#uploadStatus").val("Upload");
	}
}

function update_board() {
	var form = $("#update_board_form");
	var formData = new FormData(form[0]);
	formData.append("email", email);
	formData.append("id", id);
	var hasAlreadyFile = $("#hasAlreadyFile").val();
	var uploadStatus = $("#uploadStatus").val();
	formData.append("hasAlreadyFile", hasAlreadyFile);
	formData.append("uploadStatus", uploadStatus);

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
			location.href = "/board/" + id + "?isUpdatedBoard=true";
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
