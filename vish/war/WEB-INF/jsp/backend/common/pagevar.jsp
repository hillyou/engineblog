<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="userPrincipal" scope="request" value="<%=request.getUserPrincipal()%>"></c:set>
<c:set var="session_email" scope="request" value="${not empty userPrincipal?userPrincipal.name:''}"></c:set>
<c:set var="session_user" scope="request" value="${sessionScope[session_email]}"></c:set>
<c:set var="session_blog" scope="request" value="${session_user.currentBlog}"></c:set>
<c:set var="session_categories" scope="request" value="${session_blog.categories}"></c:set>
