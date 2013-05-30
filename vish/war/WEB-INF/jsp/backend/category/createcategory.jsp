<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>Create Category</title>
<div>
	<form action="${contextPath}/admin/category/create.html" method="post">
		<input id="name" name="name" type="text">
		<input type="hidden" id="parent" name="parent" value="${param.parent}">
		<input type="submit">
	</form>
</div>