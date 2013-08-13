<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<%@ include file="../common/include.jsp" %>
	<c:set var="blog" value="${BLOG}" scope="request"> </c:set>
	<c:set var="articles" value="${ARTICLES}"> </c:set>
	<title>${blog.title}</title>
</head>
<div id="divarticlelist">
	<%@ include file="../articlelist.jsp" %>
</div>