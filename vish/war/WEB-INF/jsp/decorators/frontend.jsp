<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ include file="pagevar.jsp" %>
<html>
<head>
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
<%@ include file="../common/footer.jsp" %>
</body>
</html>