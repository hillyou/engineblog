<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<%@ include file="common/include.jsp" %>
<title>Message</title>
</head>
<div class="message">
	<c:if test="${not empty MESSAGE }">
	    ${MESSAGE}
	</c:if>
	<c:if test="${not empty param.message }">
	    ${param.message}
	</c:if>
</div>
