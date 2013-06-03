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
			<a class="${currentCategoryClass}" href="${contextPath}/blog/${blog.name}/${rootcategory.name}.html">${rootcategory.name}</a>
		</li>
	</c:forEach>
</ul>

<c:if test="${not empty currentCategory && not empty childCategories}">
	<ul>
		<c:forEach var="childcategory" items="${childCategories }">
			<li><a href="${contextPath}/blog/${blog.name}/${childcategory.name}.html">${childcategory.name}</a></li>
		</c:forEach>
	</ul>
</c:if>
