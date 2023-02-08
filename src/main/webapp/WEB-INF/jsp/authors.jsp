<%@ page contentType="text/html;charset=UTF-8"  language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Список авторов:</h1>
<ul>
    <c:forEach  var="author" items="${requestScope.authors}" >
    <li>
        <a href="${pageContext.request.contextPath}/books?authorId=${author.id}">${author.description}</a>
    </li>

    <form method="post" action="<c:url value='/delete'/>">
        <input type="number" hidden name="id" value="${author.id}" />
        <input type="submit" name="delete" value="Удалить"/>
    </form>

    <form method="get" action="<c:url value='/update'/>">
        <input type="number" hidden name="id" value="${author.id}" />
        <input type="submit" value="Редактировать"/>
    </form>
    </c:forEach>
</ul>

<h1>Добавить автора:</h1>
<form action="${pageContext.request.contextPath}/authors" method="post">
    <label for="first_name">First_name:
        <input type="text" name="first_name" id="first_name">
    </label><br>
    <label for="last_name">Last_name:
        <input type="text" name="last_name" id="last_name">
    </label><br>
    <button type="submit">Send</button>
</form>

<h1>Добавить книгу:</h1>
<form action="${pageContext.request.contextPath}/books" method="post">
    <label for="name">Name:
        <input type="text" name="name" id="name">
    </label><br>
    <label for="year">Year:
        <input type="number" name="year" id="year">
    </label><br>
    <label for="pages">Pages:
        <input type="number" name="pages" id="pages">
    </label><br>
    <label for="author">Author:
        <input type="text" name="author" id="author">
    </label><br>
    <button type="submit">Add</button>
</form>
</body>
</html>
