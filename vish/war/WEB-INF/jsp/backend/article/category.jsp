<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Manager Category</title>
<c:set var="categories" value="${CATEGORIES}"></c:set>
<div>
	<c:forEach var="category" items="${categories}">
		<tr>
			<td>${category.name}</td>
			<td>${category.createDate}</td>
			<td><a href="${contextPath}/admin/category/newcategory.html?parent=${category.key.id}"></a></td>
		</tr>
	</c:forEach>
</div>