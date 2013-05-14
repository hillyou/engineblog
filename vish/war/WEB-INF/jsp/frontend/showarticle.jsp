<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:set var="contextPath"><%=request.getContextPath()%></c:set>
<c:set var="acticle" value="${ARTICLE}"></c:set>
<script type="text/javascript" src="${contextPath}/js/jquery/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${contextPath}/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextPath}/js/article.js"></script>
<title>${acticle.title}</title>
</head>
<body>
    <div style="width: 800px;">
       <div>${acticle.title}</div>
       <div>${acticle.content}</div>
    </div>
    <div class="commentslist">
        <c:forEach var="comment" items="${acticle.comments}">
            <div class="commentitem">
	            <div>${comment.commentBy} at <fmt:formatDate value="${comment.commentDate}" pattern="MM/dd/yyyy hh:mm" /></div>
	            <div><c:out escapeXml="true" value="${comment.content}"></c:out></div>
            </div>
        </c:forEach>
    </div>
    <div id="message"></div>
    <c:if test="${acticle.openComment }">
	    <div>
	        <form id="commentform" action="${contextPath}/article/addcomment.html" method="post">
	           <div>
	               <textarea name="comment" id="commentId" rows="5" cols="60"></textarea>
	           </div>
	           <div>
	               <input type="hidden" id="ajaxURL" value="${contextPath}/article/ajaxaddcomment.html"/>
	               <input type="hidden" name="articleid" id="articleid" value="${acticle.id}"/>
	               <input type="submit"/>
	           </div>
	        </form>
	    </div>
    </c:if>
</body>
</html>