<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="articlelist">
	<c:forEach var="instance" items="${articles}">
       <%@ include file="articlesnippt.jsp" %>
   	</c:forEach>
</div>