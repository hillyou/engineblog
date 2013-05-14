$(document).ready(function(){
	
	$('#commentform').on('submit',function() {
		var url=$('#ajaxURL').val();
		var articleId=$('#articleid').val();
		var comment=$('#commentId').val();
		$.ajax({
			url: url,
			type: "POST",
			data: "articleid="+articleId+"&comment="+comment,
			context: document.body
		}).success(function(){
			var escapecontent=escape(comment);
			var current=new Date();
			var hour=current.getHours();       
			var min=current.getMinutes();
			var dt_to = $.datepicker.formatDate('mm/dd/yy', current)+" "+hour+":"+min;
			var newcommentitme="<div class=\"commentitem\"><div> somebody at " +dt_to+"<div>"+escapecontent+"</div></div>"
			if($('.commentitem').length > 0){
				$('div .commentitem:first').before(newcommentitme)
			}else{
				$('.commentslist').append(newcommentitme)
			}
			$('#commentId').val("");
		}).done(function(msg) {
			$('#message').html(msg);
		});
		return false;
	});
	
	
});