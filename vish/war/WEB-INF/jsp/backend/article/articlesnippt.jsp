<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<ul>
	<li><a href="${contextPath}/article/showarticle/${instance.displayKey}.html" target="blank">${instance.title}</a></li>
	<li class="date"><fmt:formatDate value="${instance.createDate}" pattern="MM/dd" /></li>
	<li><a target="blank" href="${contextPath}/admin/article/updatearticle/${instance.displayKey}.html">edit</a></li>
	<li>
	<c:if test="${!instance.published}">
		<a href="${contextPath}/admin/article/publish/${instance.displayKey}.html">publish</a>
	</c:if>
	</li>
	<li><a href="${contextPath}/admin/article/del/${instance.displayKey}.html">delete</a></li>
</ul>
