<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="divlogin">
    <c:choose>
        <c:when test="${not empty intruder_email}">
            Hi, <a href="${contextPath}/admin.html">${intruder_nickname}</a> <a href="${contextPath}/site/logout.html">Logout</a>
        </c:when>
        <c:otherwise>
            <a href="${contextPath}/site/login.html">Login</a>
        </c:otherwise>
    </c:choose>
</div>