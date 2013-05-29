<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="post">
	<div class="posttitle"><a href="${contextPath}/article/showarticle/${instance.id}.html" target="blank">${instance.title}</a></div>
	<div class="date"><fmt:formatDate value="${instance.createDate}" pattern="MM/dd" /></div>
	<div><a target="blank" href="${contextPath}/admin/article/updatearticle/${instance.id}.html">edit</a></div>
	<div><a href="${contextPath}/admin/article/del/${instance.id}.html">delete</a></div>
	<div class="postcontent">
		${instance.snipptContent}
	</div>
</div>
