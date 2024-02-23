<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Servlets.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dina Kurser</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/All.css">

</head>
<body>
    <%@ include file="NavBar.jsp" %>
    <h2 class=h1a>Dina Kurser</h2>
    <form action="/studentCourse" method="get">
            <input type="submit" value="Visa Kurser" class=backbutton>
    </form>
    <table>
        <c:forEach items="${courses}" var="course">
            <table>
                <thead>
                    <tr>
                        <th>Kursnamn</th>
                        <th>YHP</th>
                        <th>Beskrivning</th>
                        <th>Studenter</th>
                        <th>Lärare</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${course.name}</td>
                        <td>${course.YHP}</td>
                        <td>${course.description}</td>
                        <td>
                            <ul>
                                <c:forEach items="${course.studentNames}" var="studentName">
                                    <li>${studentName}</li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td>
                            <ul>
                                 <c:forEach items="${course.teacherNames}" var="teacherName">
                                    <li>${teacherName}</li>
                                 </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:forEach>

    </table>
    <div style="height: 250px;"></div>
    <%@ include file="footer.jsp" %>

</body>
</html>
