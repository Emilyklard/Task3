<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div>Имя: <c:out value="${requestScope.author.first_name}"/> </div>
<div>Фамилия: <c:out value="${requestScope.author.last_name}"/> </div>

<br />

<form method="post" action="<c:url value='/update'/>">

    <label>Новое имя: <input type="text" name="first_name" /></label><br>
    <label>Новая фамилия: <input type="text" name="last_name" /></label><br>

    <input type="number" hidden name="id" value="${requestScope.author.id}"/>

    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>
