<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="articles" value="${ARTICLES}"></c:set>
<div class="inlineitems">
	<c:forEach var="instance" items="${articles}">
        <%@ include file="articlesnippt.jsp" %>
   	</c:forEach>
</div>