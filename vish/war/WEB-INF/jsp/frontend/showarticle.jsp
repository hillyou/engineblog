<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${contextPath}/js/article.js"></script>
<c:set var="acticle" value="${ARTICLE}"></c:set>
<c:set var="author" value="${AUTHOR}"></c:set>
<title>${acticle.title}</title>
<div id="divarticle">
    <div id="mainarticle">
       <div>${acticle.title}</div>
       <c:if test="${author.key eq acticle.author}">
	   		<div><a href="${contextPath}/article/user/updatearticle/${acticle.id}.html">edit</a></div>
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