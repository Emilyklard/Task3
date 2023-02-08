<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Книги выбранного автора: </h1>
<ul>
    <c:forEach var="book" items="${requestScope.books}">
        <li>${book.name}</li>
        <form method="post" action="<c:url value='/deleteBook'/>">
            <input type="number" hidden name="id" value="${book.id}" />
            <input type="submit" name="delete" value="Удалить"/>
        </form>

        <form method="get" action="<c:url value='/updateBook'/>">
            <input type="number" hidden name="id" value="${book.id}" />
            <input type="submit" value="Редактировать"/>
        </form>
    </c:forEach>

</ul>
</body>
</html>
