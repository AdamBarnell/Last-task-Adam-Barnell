package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CourseServlet", urlPatterns = {"/courses"})
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            List<Course> courses = getCoursesFromDatabase();
            request.setAttribute("courses", courses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("JSPs/Course.jsp");
            dispatcher.forward(request, response);
    }

    private List<Course> getCoursesFromDatabase() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT courses_id, name, description, YHP FROM courses";

        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("courses_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int YHP = resultSet.getInt("YHP");
                courses.add(new Course(id, name, description, YHP));
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
        String user = "Insert";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}