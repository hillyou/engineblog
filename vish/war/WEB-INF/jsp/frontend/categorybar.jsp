<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="currentCategory" value="${CURRENT_CATEGORY}"></c:set>
<c:set var="childCategories" value="${CHILD_CATEGORY}"></c:set>
<ul>
	<c:set var="currentCategoryClass" value=""></c:set>
	<c:forEach var="rootcategory" items="${blog.rootCategories }">
		<c:if test="${not empty currentCategory && rootcategory.key.id eq currentCategory.key.id}">
			<c:set var="currentCategoryClass" value="current"></c:set>
		</c:if>
		<li>
			<a class="${currentCategoryClass}" href="${contextPath}/blog/${blog.name}/${rootcategory.id}.html">${rootcategory.name}</a>
		</li>
	</c:forEach>
</ul>
