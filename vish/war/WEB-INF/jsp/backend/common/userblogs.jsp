<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${session_user.usableBlogsSize gt 0}">
		<select name="blognames" id="bloglist">
		<c:if test="${empty session_blog}">
	            <option value="">select</option>
	        </c:if>
		 <c:if test="${not empty session_blog}">
		     <option value="${session_blog.id}">${session_blog.name} -- ${session_blog.title }</option>
		 </c:if>
		 <c:forEach var="blog" items="${session_user.usableBlogs }">
		     <c:if test="${session_blog.id ne blog.id}">
		         <option value="${blog.id}">${blog.name} -- ${blog.title }</option>
		     </c:if>
		 </c:forEach>
		</select>
	 	<input type="hidden" id="switchactionurl" value="${contextPath}/admin/user/ajaxswitchblog/"/>
	</c:when>
</c:choose>
