<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"><%=request.getContextPath()%></c:set>
<c:set var="intruder" value="${CURRENT_USER}"></c:set>
<c:set var="intruder_email" value="${intruder.email}"></c:set>
<c:set var="intruder_nickname" value="${intruder.nickName}"></c:set>
<c:set var="intruder_blog" value="${intruder.currentBlog}"></c:set>
<c:set var="intruder_blogid" value="${intruder.currentBlog.id}"></c:set>