<div id="articlecommentform">
	<div id="commentmessage"></div>
	<div>
		<form id="commentform"
			action="${contextPath}/user/comment/addcomment.html" method="post">
			<div>
				<textarea name="comment" id="commentId" rows="5" cols="60"></textarea>
			</div>
			<div>
				<input type="hidden" id="ajaxURL" value="${contextPath}/user/comment/ajaxaddcomment.html" /> 
				<input type="hidden" name="articleid" id="articleid" value="${acticle.id}" />
				<input type="hidden" name="currentuser" id="currentuser" value="${loginuser.email}" />
				<input type="submit" />
			</div>
		</form>
	</div>
</div>