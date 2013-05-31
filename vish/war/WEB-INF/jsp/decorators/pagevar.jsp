<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" scope="application"><%=request.getContextPath()%></c:set>
<c:set var="userPrincipal" value="<%=request.getUserPrincipal()%>"></c:set>
<c:set var="session_email" value="${not empty userPrincipal?userPrincipal.name:''}"></c:set>
<c:set var="session_user" scope="session" value="${sessionScope[session_email]}"></c:set>
<c:set var="session_blog" scope="session" value="${session_user.currentBlog}"></c:set>
<c:set var="session_categories" scope="session" value="${session_blog.categories}"></c:set>
<c:set var="session_root_categories" scope="session" value="${session_blog.rootCategories}"></c:set>
