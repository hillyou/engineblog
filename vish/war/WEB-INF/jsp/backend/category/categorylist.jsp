<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
    <%@ include file="../common/include.jsp" %>
    <title>Manager Category</title>
</head>
<div class="inlineitems">
	<c:forEach var="category" items="${session_blog.categories}">
		<ul>
			<li>${category.name}</li>
			<li><fmt:formatDate value="${category.createDate}" pattern="MM/dd" /></li>
			<li><a href="${contextPath}/admin/category/new.html?parent=${category.displayKey}">Create a sub category</a></li>
			<li><a href="${contextPath}/admin/category/del/${category.displayKey}.html">Delete</a></li>
		</ul>
	</c:forEach>
</div>