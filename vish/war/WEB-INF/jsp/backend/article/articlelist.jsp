<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="articles" value="${ARTICLES}"></c:set>
<div id="articlelist">
	<c:forEach var="instance" items="${articles}">
	   <c:if test="${not empty session_user and session_user.key eq instance.author }">
            <%@ include file="articlesnippt.jsp" %>
       </c:if>  
   	</c:forEach>
</div>