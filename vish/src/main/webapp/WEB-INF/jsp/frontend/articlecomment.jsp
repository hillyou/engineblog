<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="commentslist">
    <c:forEach var="comment" items="${acticle.comments}">
        <div class="commentitem">
         <div>${comment.commentEmail} at <fmt:formatDate value="${comment.createDate}" pattern="MM/dd/yyyy HH:MM" /></div>
         <div class="bigblock"><c:out escapeXml="true" value="${comment.content}"></c:out></div>
        </div>
    </c:forEach>
</div>