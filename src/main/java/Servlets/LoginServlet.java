package Servlets;

import javax.servlet.http.HttpServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String anonymous = request.getParameter("anonymous");

        if ("true".equals(anonymous)) {
            HttpSession session = request.getSession();
            session.setAttribute("stateType", "anonymous");
            session.setAttribute("userType", "anonymous");
            response.sendRedirect("JSPs/HomePage.jsp");
            System.out.println("Användartyp: " + session.getAttribute("userType"));
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection connection = getDatabaseConnection();

            String teacherQuery = "SELECT * FROM teachers WHERE username = ? AND password = ?";
            PreparedStatement teacherStmt = connection.prepareStatement(teacherQuery);
            teacherStmt.setString(1, username);
            teacherStmt.setString(2, password);

            ResultSet teacherRs = teacherStmt.executeQuery();

            if (teacherRs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("stateType", "confirmed");
                session.setAttribute("userType", "teacher");
                session.setAttribute("userId", teacherRs.getInt("teachers_id"));
                response.sendRedirect("JSPs/HomePage.jsp");
                System.out.println("Användartyp: " + session.getAttribute("userType"));
            } else {
                String studentQuery = "SELECT * FROM students WHERE username = ? AND password = ?";
                PreparedStatement studentStmt = connection.prepareStatement(studentQuery);
                studentStmt.setString(1, username);
                studentStmt.setString(2, password);

                ResultSet studentRs = studentStmt.executeQuery();

                if (studentRs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("stateType", "confirmed");
                    session.setAttribute("userType", "student");
                    session.setAttribute("userId", studentRs.getInt("students_id"));
                    response.sendRedirect("JSPs/HomePage.jsp");
                    System.out.println("Användartyp: " + session.getAttribute("userType"));
                } else {
                    request.setAttribute("loginError", "Ogiltigt användarnamn eller lösenord");
                    request.getRequestDispatcher("JSPs/Login.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
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
        String user = "Insert";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }
}

