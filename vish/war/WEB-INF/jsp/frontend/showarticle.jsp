<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
	<c:set var="acticle" value="${ARTICLE}"></c:set>
	<meta name="keywords" content="${acticle.keywords}"/>
	<script type="text/javascript" src="${contextPath}/js/article.js"></script>
	<%@ include file="common/include.jsp" %>
	<title>${acticle.title}</title>
</head>
<div class="post">
    <div id="mainarticle">
       <div class="posttitle">${acticle.title}</div>
       <div class="postinfo">
	       <c:if test="${session_user.key eq acticle.author}">
		   		<div><a href="${contextPath}/admin/article/updatearticle/${acticle.displayKey}.html">edit</a></div>
		   </c:if>
	   </div>
       <div class="postcontent">${acticle.contentValue}</div>
    </div>
    
    <div class="commentlist">
    	<%@ include file="articlecomment.jsp" %>
    </div>
    
    <c:if test="${acticle.openComment }">
    <div class="commentform">
		<%@ include file="commentform.jsp" %>
	</div>
    </c:if>
</div>    