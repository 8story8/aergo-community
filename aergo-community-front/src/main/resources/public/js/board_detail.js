function deleteBoardById(id){
	$.ajax({
	    url: "/board/delete",
	    data: {id:id},               
	    type: "post",                           
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