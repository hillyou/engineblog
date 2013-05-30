<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${intruder.deleted }">
	    you have canceled yourself from this site.
    </c:when>
    <c:when test="${intruder.locked }">
        Currenty you have been locked.
    </c:when>
    <c:when test="${not intruder.valid}">
        you were not allowed to get here.
    </c:when>
    <c:when test="${not intruder.hasBlog}">
        Currenty you do not blog, click <a href="${contextPath}/admin/user/openblog.html">here</a> to crate one blog
    </c:when>
    <c:otherwise>
        <c:forEach var="blog" items="${intruder.blogs}">
            <div>${blog.title} <a href="${contextPath}/admin/article/createarticle.html?blogId=${blog.id}">crate article</a></div>
        </c:forEach>
    </c:otherwise>
</c:choose>