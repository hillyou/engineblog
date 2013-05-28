<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"  %>
<c:set var="categories" value="${CATEGORIES}"></c:set>
<title>Create Article</title>

<div id="divcreatearticle">
	<form action="${contextPath}/article/user/savearticle.html" method="post">
		<div>Title:<input name="title" id="title" type="text"/></div>
		<div>Content:<textarea id="ckeditor" name="content" rows="50" cols="80"></textarea></div>
		<div>
		<select name="category">
		  <option value="">Please select</option>
		  <c:forEach var="category" items="${categories}">
		      <option value="${category.key.id}">${category.name}</option>
		  </c:forEach>
		</select>
		</div>
		<div><input type="submit"/></div>
	</form>
</div>
<ckeditor:replace replace="ckeditor" basePath="${contextPath}/ckeditor/" />
