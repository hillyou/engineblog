<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="post">
	<div class="posttitle"><a href="${contextPath}/article/showarticle/${instance.displayKey}.html" target="blank">${instance.title}</a></div>
	<hr>
		<div class="inlineitems postinfo">
			   <ul>
			   		<%-- <li>post by ${instance.author}</li> --%>
					<li class="date"><fmt:formatDate value="${instance.createDate}" pattern="MM/dd" /></li>
					<%-- <li>Category:<a href="">${instance.category.id}</a></li>
				 	<li>|</li>
				 	<li>Comments:13</li>
				 	<li>|</li>
				 	<li>Browsed:100</li>
					<li>|</li> --%>
					<c:if test="${not empty session_user and session_user.key eq instance.author }">
						<li>|</li>
						<li><a target="blank" href="${contextPath}/admin/article/updatearticle/${instance.displayKey}.html">edit</a></li>
					</c:if>	
				</ul>
		</div>
	<hr>
	<div class="postcontent">
		<article><p>${instance.snipptContent}</p></article>
	</div>
<%-- 	<div class="inlineitems">
		 <ul>
		 	<li>Category:<a href="">${instance.category.id}</a></li>
		 	<li>|</li>
		 	<li>Comments:13</li>
		 	<li>|</li>
		 	<li>Browsed:100</li>
		 </ul>
	</div> --%>
</div>
