<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="leftpanel">
<c:choose>
	<c:when test="${session_user.validBlogger &&  not empty session_blog}">
	   <div><a href="${contextPath}/admin/article/createarticle.html">crate article</a></div>
	   <div><a href="${contextPath}/admin/category/list.html">manager category</a>
	   	<ul>
	   		<li><a href="${contextPath}/admin/category/new.html">Create</a></li>
	   	</ul>
	   </div>
    </c:when>
</c:choose>
</div>