$(document).ready(function(){
	
	$('#commentform').on('submit',function() {
		var url=$('#ajaxURL').val();
		var articleId=$('#article').val();
		var comment=$('#content').val();
		var commiter=$('#commentEmail').val();
		$.ajax({
			url: url,
			type: "POST",
			data: "article="+articleId+"&content="+comment,
			context: document.body
		}).done(function(msg) {
			$('#message').html(msg);
			if(msg.indexOf("success")!= -1){
				var escapecontent=$("<div />").text(comment).html();
				var current=new Date();
				var hour=current.getHours();       
				var min=current.getMinutes();
				var dt_to = $.datepicker.formatDate('mm/dd/yy', current)+" "+hour+":"+min;
				var newcommentitme="<div class=\"commentitem\"><div> "+commiter+" at " +dt_to+"<div  class=\"bigblock\">"+escapecontent+"</div></div>";
				if($('.commentitem').length > 0){
					$('div .commentitem:first').before(newcommentitme);
				}else{
					$('.commentslist').append(newcommentitme);
				}
				$('#content').val("");
			}
		});
		return false;
	});
	
	$('#canclesave').on('click',function() {
		
	});
	
	
	
});