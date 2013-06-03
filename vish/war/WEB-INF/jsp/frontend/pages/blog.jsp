<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<%@ include file="../common/include.jsp" %>
	<c:set var="blog" value="${BLOG}"> </c:set>
	<c:set var="articles" value="${ARTICLES}"> </c:set>
	<title>${blog.title}</title>
</head>
<div>
	<%@ include file="../categorybar.jsp" %>
</div>
<div id="divarticlelist">
	<%@ include file="../articlelist.jsp" %>
</div>