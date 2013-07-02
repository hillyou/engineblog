$(document).ready(function(){
	var currentURL=window.location.toLocaleString();
	
	$('.adminleftitems a').each(function(){
		var link=$(this).attr('href');
		if(currentURL.indexOf(link)!=-1){
			$(this).addClass("current");
		}
		
	});
	
	
});