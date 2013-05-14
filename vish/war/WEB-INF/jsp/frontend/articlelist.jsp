<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:set var="contextPath"><%=request.getContextPath()%></c:set>
<c:set var="acticles" value="${ARTICLES}"></c:set>
<script type="text/javascript" src="${contextPath}/js/jquery/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${contextPath}/js/article.js"></script>
<title>article list</title>
</head>
<body>
    <c:forEach var="instance" items="${acticles}">
	    <div>
	       <div><a href="${contextPath}/article/showarticle/${instance.id}.html">${instance.title}</a></div>
	       <div>${instance.content}</div>
	    </div>
    </c:forEach>   
</body>
</html>