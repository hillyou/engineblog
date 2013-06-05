<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="blog" value="${BLOG}"> </c:set>
<c:set var="currentCategory" value="${CURRENT_CATEGORY}"></c:set>
<c:set var="childCategories" value="${CHILD_CATEGORY}"></c:set>

<c:if test="${not empty currentCategory && not empty childCategories}">
	<ul class="subcategories">
		<c:forEach var="childcategory" items="${childCategories }">
			<li><a href="${contextPath}/blog/${blog.name}/${childcategory.displayKey}.html">${childcategory.name}</a></li>
		</c:forEach>
	</ul>
</c:if>
