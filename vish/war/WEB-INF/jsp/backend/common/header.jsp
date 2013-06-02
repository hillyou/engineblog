<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
    <c:when test="${session_user.validBlogger && session_user.usableBlogsSize gt 0}">
       <div><a href="${contextPath}/admin/user/openblog.html">crate another blog</a></div>
    </c:when>
</c:choose>