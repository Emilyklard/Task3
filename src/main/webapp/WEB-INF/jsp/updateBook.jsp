<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>


<div>Название книги: <c:out value="${requestScope.book.name}"/> </div>
<div>Год издания: <c:out value="${requestScope.book.year}"/> </div>
<div>Количество страниц: <c:out value="${requestScope.book.pages}"/> </div>

<br />

<form method="post" action="<c:url value='/updateBook'/>">

  <label>Новое название книги: <input type="text" name="name" /></label><br>
  <label>Новый год издания: <input type="number" name="year" /></label><br>
  <label>Новое количество страниц: <input type="number" name="pages" /></label><br>

  <input type="number" hidden name="id" value="${requestScope.book.id}"/>

  <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>