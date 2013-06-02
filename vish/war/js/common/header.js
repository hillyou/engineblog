$(document).ready(function(){
	
	$("#bloglist").on("change",function(){
		var blogid=$(this).val();
		var url=$("#switchactionurl").val()+blogid+".html";
		$.ajax({
			url: url,
			context: document.body
		}).success(function(){
			window.location.reload(true);
		}).done(function(msg) {
		});
	});
	
	
	
	
});