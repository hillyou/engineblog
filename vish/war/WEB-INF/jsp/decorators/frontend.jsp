<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ include file="pagevar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="include.jsp" %>
<link href="${contextPath}/styles/frontend.css" rel="stylesheet" type="text/css"/>
<decorator:head/>
<title><decorator:title></decorator:title></title>
</head>
<body>
<div id="header">
	<%@ include file="../common/header.jsp" %>
</div>
<div id="frontpage">
	<div id="frontheader">
		<jsp:include page="../frontend/common/header.jsp"></jsp:include>
	</div>

	<div id="frontleftpanel">
		<jsp:include page="../frontend/common/leftpanel.jsp"></jsp:include>
	</div>
	<div id="frontrightpanel">
		<jsp:include page="../frontend/common/rightpanel.jsp"></jsp:include>
	</div>
	<div id="frontbody">
		<decorator:body></decorator:body>
	</div>
	<div id="frontfooter">
		<jsp:include page="../frontend/common/footer.jsp"></jsp:include>
	</div>
</div>
<div id="footer">
	<%@ include file="../common/footer.jsp" %>
</div>
</body>
</html>