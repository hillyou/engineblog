<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Create Article</title>
</head>
<body>
    <c:set var="contextPath"><%=request.getContextPath()%></c:set>
    <c:set var="acticle" value="${ARTICLE}"></c:set>
    <div>
        <form action="${contextPath}/article/updatearticle.html" method="post">
            <div>Title:<input name="title" type="text" value="${acticle.title}"/></div>
            <div>Content:<textarea id="ckeditor" name="content" rows="30" cols="80">${acticle.content}</textarea></div>
            <div>
	            <input type="hidden" name="articleid" value="${acticle.id}"/>
	            <input type="submit"/>
            </div>
        </form>
    </div>
    <ckeditor:replace replace="ckeditor" basePath="${contextPath}/ckeditor/" />
</body>
</html>