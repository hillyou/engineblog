<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="divlogin">
    <c:choose>
        <c:when test="${not empty session_user.key}">
            Hi, <a href="${contextPath}/admin.html">${session_user.nickName}</a>
            <c:if test="${not empty session_blog}">
	     		<a href="${contextPath}/blog/${fn:trim(session_blog.name)}.html">My Blog</a>
	 		</c:if>
            <%@ include file="userblogs.jsp" %>
            <a href="${contextPath}/site/logout.html">Logout</a>
        </c:when>
        <c:otherwise>
            <a href="${contextPath}/site/login.html">Login</a>
        </c:otherwise>
    </c:choose>
</div>
