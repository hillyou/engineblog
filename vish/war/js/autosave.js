$(document).ready(function() {
	var contextPath = $('#contextPath').val();
	var data = "";
	$(":input[name]").each(function() {
		var name = $(this).attr("name");
		var val = $(this).val();
		var pair = name + "=" + val;
		data += pair + "&";
	});
	data=data.substring(0,data.length-1);
	setInterval($.ajax({
		type : "POST",
		url : contextPath + "/admin/article/ajaxsavedraft.html",
		data : data,
		dataType : "json",
		cache : false,
		contentType : "application/json",
	}).done(function(resp) {
		alert(resp.articleId+" <> " + resp.message);
	}), 15000);

});