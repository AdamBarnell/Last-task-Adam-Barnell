<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Servlets.BeanStudents" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="sv">
<head>
    <title>Studenter</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/All.css">
</head>
<body>
<%@ include file="NavBar.jsp" %>
<h2 class=h1a>Alla Studenter</h2>
<form action="${pageContext.request.contextPath}/students" method="get">
    <input type="submit" value="Hämta Alla Studenter">
</form>
<table>
    <tr>
        <th>ID</th>
        <th>Namn</th>
        <th>Stad</th>
        <th>Hobby</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.town}</td>
            <td>${student.hobby}</td>
        </tr>
    </c:forEach>
</table>
<div style="height: 250px;"></div>
<%@ include file="footer.jsp" %>
</body>
</html>
