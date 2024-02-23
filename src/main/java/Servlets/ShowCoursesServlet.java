package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "studentCourse", urlPatterns = {"/studentCourse"})
public class ShowCoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (!"student".equals(session.getAttribute("userType"))) {
            response.sendRedirect("JSPs/Login.jsp");
            return;
        }

        try {
            List<Course> courses = getCoursesForStudent(userId);
            request.setAttribute("courses", courses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("JSPs/studentCourse.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    private List<Course> getCoursesForStudent(int studentId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = getDatabaseConnection();
             PreparedStatement stmtCourses = connection.prepareStatement(
                     "SELECT c.courses_id, c.name AS course_name, c.description, c.YHP " +
                             "FROM courses c JOIN attendance a ON c.courses_id = a.course_id " +
                             "JOIN students s ON a.student_id = s.students_id " +
                             "WHERE s.students_id = ?")) {

            stmtCourses.setInt(1, studentId);
            ResultSet rsCourses = stmtCourses.executeQuery();

            while (rsCourses.next()) {
                Course course = new Course(
                        rsCourses.getInt("courses_id"),
                        rsCourses.getString("course_name"),
                        rsCourses.getString("description"),
                        rsCourses.getInt("YHP"));

                addStudentsToCourse(connection, course);
                addTeachersToCourse(connection, course);

                courses.add(course);
            }
        }
        return courses;
    }

    private void addStudentsToCourse(Connection connection, Course course) throws SQLException {
        try (PreparedStatement stmtStudents = connection.prepareStatement(
                "SELECT s.name FROM students s JOIN attendance a ON s.students_id = a.student_id WHERE a.course_id = ?")) {
            stmtStudents.setInt(1, course.getId());
            ResultSet rsStudents = stmtStudents.executeQuery();
            while (rsStudents.next()) {
                course.addStudentName(rsStudents.getString("name"));
            }
        }
    }

    private void addTeachersToCourse(Connection connection, Course course) throws SQLException {
        try (PreparedStatement stmtTeachers = connection.prepareStatement(
                "SELECT t.name FROM teachers t JOIN teachers_attendence ta ON t.teachers_id = ta.teachers_id WHERE ta.courses_id = ?")) {
            stmtTeachers.setInt(1, course.getId());
            ResultSet rsTeachers = stmtTeachers.executeQuery();
            while (rsTeachers.next()) {
                course.addTeacherName(rsTeachers.getString("name"));
            }
        }
    }



    private Connection getDatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        String url = "jdbc:mysql://localhost:3306/gritacademy";
        String user = "user";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}

