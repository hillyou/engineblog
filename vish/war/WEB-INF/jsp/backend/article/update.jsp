<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"  %>
<head>
    <%@ include file="../common/include.jsp" %>
    <link href="${contextPath}/styles/noright.css" rel="stylesheet" type="text/css"/>
    <title>Update Article</title>
</head>

<c:set var="acticle" value="${ARTICLE}"></c:set>
<div><a href="${contextPath}/admin/article/createarticle.html">write another article</a></div>
<div>${MESSAGE}</div>
<div>
    <form action="${contextPath}/admin/article/updatearticle.html" method="post">
        <div>Title:<input name="title" class="w80p" type="text" value="${acticle.title}"/></div>
        <div>Content:<textarea id="ckeditor" name="content" rows="50" cols="60">${acticle.contentValue}</textarea></div>
        <div>
         <div>
	        <select name="category">
	          <option value="">Please select</option>
	          <c:forEach var="category" items="${session_categories}">
	              <option value="${category.displayKey}" ${(acticle.category.id eq category.key.id)?'selected=\"selected\"':''}>${category.name}</option>
	          </c:forEach>
	        </select>
         </div>
         <div>
            <input type="text" id="keywords" name="keywords" value="${acticle.keywords}">
         </div>
         <input type="hidden" name="key" value="${acticle.displayKey}"/>
         <input type="submit"/><input type="button" id="canclesave" value="cancle"/>
        </div>
    </form>
</div>
<ckeditor:replace replace="ckeditor" basePath="${contextPath}/ckeditor/" />
