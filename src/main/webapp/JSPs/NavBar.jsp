<nav class='topnav'>
    <ul>
        <li class='homebutton'><a href="${pageContext.request.contextPath}http://localhost:9090/JSPs/HomePage.jsp"><img src='https://gritacademy.se/wp-content/uploads/2021/05/Grit-Academy-logo.png' width='100' height='50' alt='GritacademyLogo'/></a></li>
        <li><a href="${pageContext.request.contextPath}http://localhost:9090/JSPs/HomePage.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}http://localhost:9090/JSPs/Course.jsp">Courses</a></li>
        <c:if test="${sessionScope.userType == 'teacher'}">
            <li><a href="${pageContext.request.contextPath}http://localhost:9090/JSPs/teacherDashboard.jsp">Teacherpanel</a></li>
            <li><a href="${pageContext.request.contextPath}http://localhost:9090/JSPs/studentCourses.jsp">Search for student</a></li>
            <li><a href="${pageContext.request.contextPath}http://localhost:9090/JSPs/Students.jsp">Students</a></li>
        </c:if>
        <c:if test="${sessionScope.userType == 'student'}">
            <li><a href="${pageContext.request.contextPath}http://localhost:9090/JSPs/studentCourse.jsp">Your courses</a></li>
        </c:if>
        <c:if test="${sessionScope.userType != null}">
                    <!-- Logout Button -->
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        </c:if>
    </ul>
</nav>
