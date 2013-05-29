<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="post">
	<div class="posttitle"><a href="${contextPath}/article/showarticle/${instance.id}.html" target="blank">${instance.title}</a></div>
	<div class="date"><fmt:formatDate value="${instance.createDate}" pattern="MM/dd" /></div>
	<div><a target="blank" href="${contextPath}/admin/article/updatearticle/${instance.id}.html">edit</a></div>
	<c:if test="${!instance.published}">
		<div><a href="${contextPath}/admin/article/publish/${instance.id}.html">publish</a></div>
	</c:if>
	<div><a href="${contextPath}/admin/article/del/${instance.id}.html">delete</a></div>
	<div class="postcontent">
		${instance.snipptContent}
	</div>
</div>
