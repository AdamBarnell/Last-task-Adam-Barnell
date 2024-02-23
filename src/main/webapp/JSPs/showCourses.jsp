<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Servlets.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kurser för vald student</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/All.css">

</head>
<body>
    <h2>Kurser</h2>
    <%@ include file="NavBar.jsp" %>
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
                <td>${course.yhp}</td>
            </tr>
        </c:forEach>
    </table>
    <div style="height: 250px;"></div>
    <%@ include file="footer.jsp" %>

</body>
</html>
