<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>Manager Category</title>
<div>
    <table>
	<c:forEach var="category" items="${session_blog.categories}">
		<tr>
			<td>${category.name}</td>
			<td><fmt:formatDate value="${category.createDate}" pattern="MM/dd" /></td>
			<td><a href="${contextPath}/admin/category/new.html?parent=${category.displayKey}">Create a sub category</a></td>
			<td><a href="${contextPath}/admin/category/del/${category.displayKey}.html">Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</div>