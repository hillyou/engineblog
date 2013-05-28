<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>Manager Category</title>
<c:set var="categories" value="${CATEGORIES}"></c:set>
<div>
<a href="${contextPath}/admin/category/newcategory.html">Create a category</a>
    <table>
	<c:forEach var="category" items="${categories}">
		<tr>
			<td>${category.name}</td>
			<td><fmt:formatDate value="${category.createDate}" pattern="MM/dd" /></td>
			<td><a href="${contextPath}/admin/category/newcategory.html?parent=${category.key.id}">Create a sub category</a></td>
		</tr>
	</c:forEach>
	</table>
</div>