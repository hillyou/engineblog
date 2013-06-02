<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
	<c:set var="acticle" value="${ARTICLE}"></c:set>
	<meta name="keywords" content="${acticle.keywords}"/>
	<script type="text/javascript" src="${contextPath}/js/article.js"></script>
	<%@ include file="common/include.jsp" %>
	<title>${acticle.title}</title>
</head>
<div id="divarticle">
    <div id="mainarticle">
       <div>${acticle.title}</div>
       <c:if test="${session_user.key eq acticle.author}">
	   		<div><a href="${contextPath}/admin/article/updatearticle/${acticle.displayKey}.html">edit</a></div>
	   </c:if>
       <div>${acticle.contentValue}</div>
    </div>
    
    <div id="divcommentlist">
    	<%@ include file="articlecomment.jsp" %>
    </div>
    
    <c:if test="${acticle.openComment }">
    <div id="divcomment">
		<%@ include file="commentform.jsp" %>
	</div>
    </c:if>
</div>    