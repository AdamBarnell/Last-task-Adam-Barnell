<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Servlets.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kurser och Lärare</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/All.css">
</head>
<body>
<%@ include file="NavBar.jsp" %>
<h2 class=h1a>Kurser och deras Lärare</h2>
<form action="${pageContext.request.contextPath}/coursesWithTeachers" method="get">
    <input type="submit" value="Hämta Kurser och Lärare">
</form>
<table>
    <tr>
        <th>Kursnamn</th>
        <th>Beskrivning</th>
        <th>YHP</th>
        <th>Lärare</th>
    </tr>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.name}</td>
            <td>${course.description}</td>
            <td>${course.YHP}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty course.teacherNames}">
                        <ul>
                            <c:forEach items="${course.teacherNames}" var="teacherName">
                                <li>${teacherName}</li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        Ingen lärare tilldelad
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
<div style="height: 250px;"></div>
<%@ include file="footer.jsp" %>
</body>
</html>
