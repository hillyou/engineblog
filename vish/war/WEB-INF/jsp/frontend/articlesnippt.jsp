<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="post">
	<div class="posttitle"><a href="${contextPath}/article/showarticle/${instance.displayKey}.html" target="blank">${instance.title}</a></div>
	<div class="date"><fmt:formatDate value="${instance.createDate}" pattern="MM/dd" /></div>
	<c:if test="${not empty session_user and session_user.key eq instance.author }">
		<div><a target="blank" href="${contextPath}/admin/article/updatearticle/${instance.displayKey}.html">edit</a></div>
	</c:if>	
	<div class="postcontent">
		${instance.snipptContent}
	</div>
</div>
