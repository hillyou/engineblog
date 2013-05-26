<div id="articlecommentform">
	<div id="commentmessage"></div>
	<div>
		<form id="commentform"
			action="${contextPath}/article/user/addcomment.html" method="post">
			<div>
				<textarea name="comment" id="commentId" rows="5" cols="60"></textarea>
			</div>
			<div>
				<input type="hidden" id="ajaxURL" value="${contextPath}/article/user/ajaxaddcomment.html" /> 
				<input type="hidden" name="articleid" id="articleid" value="${acticle.id}" />
				<input type="submit" />
			</div>
		</form>
	</div>
</div>