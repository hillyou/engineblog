<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
<head>
<c:set var="contextPath"><%=request.getContextPath()%></c:set>
<c:set var="onlineuser" value="${CURRENT_USER}"></c:set>
<c:set var="nickName">${not empty onlineuser?onlineuser.nickName:"" }</c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="include.jsp" %>
<title><decorator:title></decorator:title></title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div id="divbody">
	<jsp:include page="../frontend/common/header.jsp"></jsp:include>
	<decorator:body></decorator:body>
	<jsp:include page="../frontend/common/footer.jsp"></jsp:include>
</div>
</body>
</html>