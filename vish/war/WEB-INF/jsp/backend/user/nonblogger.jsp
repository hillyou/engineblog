<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${session_user.deleted }">
	    you have canceled yourself from this site.
    </c:when>
    <c:when test="${session_user.locked }">
        Currenty you have been locked.
    </c:when>
    <c:when test="${not session_user.valid}">
        you were not allowed to get here.
    </c:when>
    <c:when test="${not session_user.hasBlog}">
        Currenty you do not blog, click <a href="${contextPath}/admin/user/openblog.html">here</a> to crate one blog
    </c:when>
    <c:otherwise>
        <div><a href="${contextPath}/admin/user/openblog.html">crate another blog</a></div>
        <div>${session_blog.title} <a href="${contextPath}/admin/article/createarticle.html">crate article</a></div>
    </c:otherwise>
</c:choose>