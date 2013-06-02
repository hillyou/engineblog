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
    <c:when test="${session_user.validBlogger && not session_user.hasBlog}">
        Currenty you do not blog, click <a href="${contextPath}/admin/user/openblog.html">here</a> to crate one blog
    </c:when>
</c:choose>