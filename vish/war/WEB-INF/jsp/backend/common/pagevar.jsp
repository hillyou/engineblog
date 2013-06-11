<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="userPrincipal" value="<%=request.getUserPrincipal()%>"></c:set>
<c:set var="session_email" value="${not empty userPrincipal?userPrincipal.name:''}"></c:set>
<c:set var="session_user" scope="request" value="${sessionScope[session_email]}"></c:set>
<c:if test="${not empty session_user}">
    <c:set var="session_blog" scope="request" value="${session_user.currentBlog}"></c:set>
</c:if>
<c:if test="${not empty session_blog}">
    <c:set var="session_categories" scope="request" value="${session_blog.categories}"></c:set>
</c:if>
