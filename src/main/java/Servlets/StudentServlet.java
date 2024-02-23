package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "studentsServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);


        if (session != null && "teacher".equals(session.getAttribute("userType"))) {
            List<BeanStudents> students = getStudentsFromDatabase();
            request.setAttribute("students", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JSPs/Students.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "JSPs/Login.jsp");
        }
    }

    private List<BeanStudents> getStudentsFromDatabase() {
        List<BeanStudents> students = new ArrayList<>();
        String sql = "SELECT students_id, name, town, hobby FROM students";

        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("students_id");
                String name = resultSet.getString("name");
                String town = resultSet.getString("town");
                String hobby = resultSet.getString("hobby");
                students.add(new BeanStudents(id, name, town, hobby));
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

