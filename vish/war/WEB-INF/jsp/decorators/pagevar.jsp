<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" scope="session"><%=request.getContextPath()%></c:set>
<c:set var="intruder" scope="session" value="${CURRENT_USER}"></c:set>
<c:set var="intruder_email" scope="session" value="${intruder.email}"></c:set>
<c:set var="intruder_nickname" scope="session" value="${intruder.nickName}"></c:set>
<c:set var="intruder_blog" scope="session" value="${intruder.currentBlog}"></c:set>
<c:set var="intruder_blogid" scope="session" value="${intruder.currentBlog.id}"></c:set>
<c:set var="DEBUG" value="${true}" scope="session"></c:set>
<c:if test="${DEBUG}">
<table>
<tr><td>contextPath:${contextPath }</td></tr>
<tr><td>intruder:${intruder }</td></tr>	
<tr><td>intruder_email:${intruder_email }</td></tr>	
<tr><td>intruder_nickname:${intruder_nickname }</td></tr>	
<tr><td>intruder_blog:${intruder_blog }</td></tr>	
<tr><td>intruder_blogid:${intruder_blogid }</td></tr>	
<tr><td>intruder_hasBlog:${intruder.hasBlog }</td></tr>	
</table>
</c:if>