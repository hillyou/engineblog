<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="acticles" value="${ARTICLES}"></c:set>
<c:set var="author" value="${AUTHOR}"></c:set>
<div id="articlelist">
	<c:forEach var="instance" items="${acticles}">
       <%@ include file="articlesnippt.jsp" %>
   	</c:forEach>
</div>