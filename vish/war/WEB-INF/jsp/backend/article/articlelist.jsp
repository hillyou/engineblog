<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="acticles" value="${ARTICLES}"></c:set>
<c:set var="author" value="${CURRENT_USER}"></c:set>
<div id="articlelist">
	<c:forEach var="instance" items="${acticles}">
	   <c:if test="${not empty author and author.key eq instance.author }">
            <%@ include file="articlesnippt.jsp" %>
       </c:if>  
   	</c:forEach>
</div>