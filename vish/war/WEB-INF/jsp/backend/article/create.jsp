<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"  %>
<head>
	<%@ include file="../common/include.jsp" %>
	<link href="${contextPath}/styles/noright.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/autosave.js" type="text/javascript" ></script>
	<title>Create Article</title>
</head>

<div id="divcreatearticle">
	<form action="${contextPath}/admin/article/savearticle.html" method="post">
		<div>Title:<input name="title" class="w80p" type="text"/></div>
		<div>Content:<textarea id="ckeditor" name="content" rows="50" cols="60"></textarea></div>
		<div>
			<select name="category">
			  <option value="">Please select</option>
			  <c:forEach var="category" items="${session_categories}">
			      <option value="${category.displayKey}">${category.name}</option>
			  </c:forEach>
			</select>
		</div>
		<div>
		  <input type="text" id="keywords" name="keywords">
		</div>
		<div>
		  <input type="hidden" id="key" name="key" value="">
		  <input type="hidden" id="updateurl" value="${contextPath}/admin/article/createarticle.html">
		  <input type="submit"/>
		</div>
	</form>
</div>
<ckeditor:replace replace="ckeditor" basePath="${contextPath}/ckeditor/" />
