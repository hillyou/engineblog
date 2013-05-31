<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${session_user.usableBlogsSize gt 1}">
		<select name="blognames">
		<c:if test="${empty session_blog}">
             <option value="">select</option>
         </c:if>
		 <c:if test="${not empty session_blog}">
		     <option value="${session_blog.id}">${session_blog.name}</option>
		 </c:if>
		 <c:forEach var="blog" items="${session_user.usableBlogs }">
		     <c:if test="${session_blog.id ne blog.id}">
		         <option value="${blog.id}">${blog.name}</option>
		     </c:if>
		 </c:forEach>
		</select>
	</c:when>
</c:choose>
