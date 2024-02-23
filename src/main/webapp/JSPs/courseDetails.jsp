<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Servlets.Course, Servlets.Student, Servlets.Teacher" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kursdetaljer</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/All.css">
</head>
<body>
<%@ include file="NavBar.jsp" %>
<h2>Kursdetaljer</h2>
<h3>${course.name} (${course.description}, YHP: ${course.YHP})</h3>

<h4>Elever:</h4>
<ul>
    <c:forEach items="${students}" var="student">
        <li>${student.name}</li>
    </c:forEach>
</ul>

<h4>Lärare:</h4>
<ul>
    <c:forEach items="${teachers}" var="teacher">
        <li>${teacher.name}</li>
    </c:forEach>
</ul>
<div style="height: 250px;"></div>
<%@ include file="footer.jsp" %>

</body>
</html>
