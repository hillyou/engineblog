<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"  %>

<title>Update Article</title>

<c:set var="acticle" value="${ARTICLE}"></c:set>
<div><a href="${contextPath}/article/user/createarticle.html">write another article</a></div>
<div>${MESSAGE}</div>
<div>
    <form action="${contextPath}/article/user/updatearticle.html" method="post">
        <div>Title:<input name="title" id="title" type="text" value="${acticle.title}"/></div>
        <div>Content:<textarea id="ckeditor" name="content" rows="50" cols="80">${acticle.contentValue}</textarea></div>
        <div>
         <input type="hidden" name="articleid" value="${acticle.id}"/>
         <input type="submit"/><input type="button" id="canclesave" value="cancle"/>
        </div>
    </form>
</div>
<ckeditor:replace replace="ckeditor" basePath="${contextPath}/ckeditor/" />
