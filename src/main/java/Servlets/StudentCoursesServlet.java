package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentCoursesServlet", urlPatterns = "/studentCourses")
public class StudentCoursesServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        List<BeanStudents> students = getAllStudents();
        getServletContext().setAttribute("students", students);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (!"teacher".equals(session.getAttribute("userType"))) {
            response.sendRedirect("JSPs/Login.jsp");
            return;
        }
        String studentIdStr = request.getParameter("studentId");
        if (studentIdStr != null && !studentIdStr.isEmpty()) {
            int studentId = Integer.parseInt(studentIdStr);
            List<Course> courses = getCoursesForStudent(studentId);
            request.setAttribute("courses", courses);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSPs/studentCourses.jsp");
        dispatcher.forward(request, response);
    }

    private List<Course> getCoursesForStudent(int studentId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.courses_id, c.name, c.description, c.YHP FROM courses c JOIN attendance a ON c.courses_id = a.course_id WHERE a.student_id = ?";

        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course(
                            resultSet.getInt("courses_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("YHP")
                    );
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }



    private List<BeanStudents> getAllStudents() {
        List<BeanStudents> students = new ArrayList<>();
        String sql = "SELECT students_id, name, town, hobby FROM students";

        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                BeanStudents student = new BeanStudents(
                        resultSet.getInt("students_id"),
                        resultSet.getString("name"),
                        resultSet.getString("town"),
                        resultSet.getString("hobby")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
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
