<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Create Category</title>
<div>
	<form action="${contextPath}/admin/category/create.html" method="post">
		<input id="catetoryname" name="categoryname" type="text">
		<input type="hidden" id="parentcategory" name="parentcategory" value="${param.parent}">
		<input type="submit">
	</form>
</div>