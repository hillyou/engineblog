<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${contextPath}/styles/base.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath}/styles/common.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Message</title>
</head>
<body>
    <div class="message">
	    <c:if test="${not empty MESSAGE }">
	        ${MESSAGE}
	    </c:if>
	    <c:if test="${not empty param.message }">
	        ${param.message}
	    </c:if>
    </div>
</body>
</html>