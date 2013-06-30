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
       <hr>
       <div class="inlineitems postinfo">
	       <ul>
	           <li class="date"><fmt:formatDate value="${acticle.createDate}" pattern="MM/dd" /></li>
		       <c:if test="${session_user.key eq acticle.author}">
			   		<li><a href="${contextPath}/admin/article/updatearticle/${acticle.displayKey}.html">edit</a></li>
			   </c:if>
		   </ul>
	   </div>
	   <hr>
       <div class="bigblock postcontent"><article>${acticle.contentValue}</article></div>
    </div>
    
    <div class="commentlist">
    	<%@ include file="articlecomment.jsp" %>
    </div>
    
<%--     <c:if test="${acticle.openComment }">
    <div class="commentform">
		<%@ include file="commentform.jsp" %>
	</div>
    </c:if> --%>
    <wb:comments url="auto" brandline="y" width="auto" appkey="1257707402" ralateuid="3541674877" ></wb:comments>
</div>    