<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>Manager Category</title>
<div>
<a href="${contextPath}/admin/category/new.html">Create a category</a>
    <table>
	<c:forEach var="category" items="${session_categories}">
		<tr>
			<td>${category.name}</td>
			<td><fmt:formatDate value="${category.createDate}" pattern="MM/dd" /></td>
			<td><a href="${contextPath}/admin/category/new.html?parent=${category.class.simpleName}:${category.key.id}">Create a sub category</a></td>
			<td><a href="${contextPath}/admin/category/del/${category.key.id}.html">Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</div>