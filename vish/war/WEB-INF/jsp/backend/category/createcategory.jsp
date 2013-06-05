<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
    <%@ include file="../common/include.jsp" %>
    <title>Create Category</title>
</head>
<div>
	<form action="${contextPath}/admin/category/create.html" method="post">
		<input id="name" name="name" type="text">
		<input type="hidden" id="parentKey" name="parentKey" value="${param.parent}">
		<input type="submit">
	</form>
</div>