<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="post">
	<div class="posttitle"><a href="${contextPath}/article/showarticle/${instance.id}.html" target="blank">${instance.title}</a></div>
	<div class="date"><fmt:formatDate value="${instance.createDate}" pattern="MM/dd" /></div>
	<c:if test="${not empty author and author.key eq instance.author }">
		<div><a target="blank" href="${contextPath}/article/user/updatearticle/${instance.id}.html">edit</a></div>
	</c:if>	
	<div class="postcontent">
		${instance.snipptContent}
	</div>
</div>
