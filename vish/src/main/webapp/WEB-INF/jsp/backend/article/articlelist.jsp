<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <%@ include file="../common/include.jsp" %>
    <title>Manager Articles</title>
</head>

<c:set var="articles" value="${ARTICLES}"></c:set>
<div class="inlineitems adminlist">
	<c:forEach var="instance" items="${articles}">
        <%@ include file="articlesnippt.jsp" %>
   	</c:forEach>
</div>