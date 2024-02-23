<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Servlets.Course" %>
<%@ page import="Servlets.BeanStudents" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="sv">
<meta charset="UTF-8">
<head>
    <title>Sök Kurser för Elev</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/All.css">

</head>
<body>
<%@ include file="NavBar.jsp" %>
    <h2 class=h1a>Välj en elev för att se dess kurser</h2>
    <form action="/studentCourses" method="get">
        <select name="studentId">
            <c:forEach items="${applicationScope.students}" var="student">
                <option value="${student.id}">${student.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Visa Kurser">
    </form>

    <h2 class=h2a>Kurser för vald student</h2>
    <table>
        <tr>
            <th>Kursnamn</th>
            <th>Beskrivning</th>
            <th>YHP</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.name}</td>
                <td>${course.description}</td>
                <td>${course.YHP}</td>
            </tr>
        </c:forEach>
    </table>
    <div style="height: 250px;"></div>
    <%@ include file="footer.jsp" %>
</body>
</html>
