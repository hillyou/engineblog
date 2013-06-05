<head>
    <%@ include file="../common/include.jsp" %>
    <title>New Blog</title>
</head>

<div id="blogform">
    <form action="${contextPath}/admin/user/openblog.html" method="post">
        name:<input type="text" name="name" id="blogname"/>
        title:<input type="text" name="title" id="blogtitle"/>
        <input type="submit"/>
    </form>
</div>
