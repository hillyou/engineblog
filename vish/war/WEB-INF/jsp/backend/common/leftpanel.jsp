<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="listitems adminleftitems">
<ul>
<c:choose>
	<c:when test="${session_user.validBlogger &&  not empty session_blog}">
	   <li><a href="${contextPath}/admin/article/createarticle.html">create article</a></li>
	   <li><a href="${contextPath}/admin/article/articlelist.html">manager article</a></li>
   	   <li><a href="${contextPath}/admin/category/new.html">create root category</a></li>
	   <li><a href="${contextPath}/admin/category/list.html">manager category</a></li>
    </c:when>
</c:choose>
</ul>  
</div>