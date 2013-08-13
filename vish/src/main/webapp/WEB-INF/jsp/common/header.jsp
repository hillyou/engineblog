<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="divlogin">
    <c:choose>
        <c:when test="${not empty session_user.key && session_user.admin}">
            Hi, <a href="${contextPath}/admin.html">${session_user.nickName}</a>
            <c:if test="${not empty session_blog}">
	     		<a href="${contextPath}/blog/${session_blog.name}.html">${session_blog.name}</a>
	 		</c:if>
            <a href="${contextPath}/site/logout.html">Logout</a>
        </c:when>
        <c:when test="${not empty session_user.key && not session_user.admin}">
            Welcome, ${session_user.nickName}
            <a href="${contextPath}/site/logout.html">Logout</a>
        </c:when>
        <c:otherwise>
            <a href="${contextPath}/site/login.html">Login</a>
        </c:otherwise>
    </c:choose>
</div>
