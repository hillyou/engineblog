$(document).ready(function() {
	
	function saveDraft(){
		var contextPath = $('#contextPath').val();
		var data = "";
		var content = CKEDITOR.instances.ckeditor.getData();
		$(":input[name]").each(function() {
			var name = $(this).attr("name");
			if(name!=='content'){
				var val = $(this).val();
				var pair ="'"+name + "':'" + val+"'";
				data += pair + ",";
			}
		});
		data+="'content':'"+content+"'";
		$.ajax({
			type : "POST",
			url : contextPath + "/admin/article/ajaxsavedraft.html",
			data : "{ "+data+" }",
			dataType : "json",
			cache : false,
			contentType : "application/json;charset=UTF-8",
		}).done(function(jsonresp) {
			alert(jsonresp.articleId+" <> " + jsonresp.message);
		});
	}
	setInterval(saveDraft, 60000);
});