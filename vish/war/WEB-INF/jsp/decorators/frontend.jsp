<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
<head>
<c:set var="contextPath"><%=request.getContextPath()%></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${contextPath}/styles/base.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath}/styles/common.css" rel="stylesheet" type="text/css"/>
<script src="${contextPath}/js/jquery/jquery.js" type="text/javascript"></script>
<script src="${contextPath}/js/jquery/jquery-ui.js" type="text/javascript" ></script>
<title><decorator:title></decorator:title></title>
</head>
<body>
<div id="divbody">
	<jsp:include page="../frontend/common/header.jsp"></jsp:include>
	<decorator:body></decorator:body>
	<jsp:include page="../frontend/common/footer.jsp"></jsp:include>
</div>
</body>
</html>