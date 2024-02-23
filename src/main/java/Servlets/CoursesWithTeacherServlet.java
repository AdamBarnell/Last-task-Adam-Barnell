package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "CoursesWithTeachersServlet", urlPatterns = {"/coursesWithTeachers"})
public class CoursesWithTeacherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && "teacher".equals(session.getAttribute("userType"))) {
            List<Course> courses = getCoursesWithTeachersFromDatabase();
            request.setAttribute("courses", courses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JSPs/teacherDashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "JSPs/Login.jsp");
        }

    }

    private List<Course> getCoursesWithTeachersFromDatabase() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.courses_id, c.name AS course_name, c.description, c.YHP, \n" +
                "GROUP_CONCAT(t.name SEPARATOR ', ') AS teacherNames\n" +
                "FROM courses c\n" +
                "LEFT JOIN teachers_attendence ta ON c.courses_id = ta.courses_id\n" +
                "LEFT JOIN teachers t ON ta.teachers_id = t.teachers_id\n" +
                "GROUP BY c.courses_id;\n";

        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("courses_id");
                String name = resultSet.getString("course_name");
                String description = resultSet.getString("description");
                int YHP = resultSet.getInt("YHP");
                String teacherNames = resultSet.getString("teacherNames");

                Course course = new Course(id, name, description, YHP);
                course.setTeacherNames(Collections.singletonList(teacherNames != null ? teacherNames : "Ingen tilldelad lärare"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private Connection getDatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        String url = "jdbc:mysql://localhost:3306/gritacademy";
        String user = "teacher";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
