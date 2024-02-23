<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Servlets.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kurser</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/All.css">

</head>
<body>
<%@ include file="NavBar.jsp" %>
    <h2 class=h1a>Alla Kurser</h2>
    <table>
    <form action="/courses" method="get" >
        <input type="submit" value="Hämta Alla Kurser" class=backbutton>
    </form>
            <tr>
                <th>ID</th>
                <th>Namn</th>
                <th>Beskrivning</th>
                <th>YHP</th>
            </tr>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.id}</td>
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

