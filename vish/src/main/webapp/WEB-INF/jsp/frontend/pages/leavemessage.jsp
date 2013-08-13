<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<%@ include file="../common/include.jsp" %>
	<title>Send a message to Admin</title>
</head>
<div>
	<form action="${contextPath}/_ah/xmpp/message/chat" method="post" enctype="multipart/form-data">
	   <c:choose>
	       <c:when test="${not empty session_email }">
	           <input type="hidden" name="from" value="${session_email}">
	       </c:when>
	       <c:otherwise>
	           <input type="text" name="from" value="">
	       </c:otherwise>
	   </c:choose>
	   <textarea rows="10" cols="60" name="body"></textarea>
	   <input type="hidden" name="to" value="hillyou@gmail.com">
	   <input type="submit">
	</form>
</div>