package com.employeelogin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("Username");
        String password = request.getParameter("password");

        boolean isValidUser = false;
        String task = null; // to store employee's task

        try {
            // 1. Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to DB
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeedb", "root", "1234");

            // 3. Prepare statement
            PreparedStatement ps = con.prepareStatement(
                    "SELECT task FROM employee WHERE Username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            // 4. Execute query
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValidUser = true;
                task = rs.getString("task"); // get the task column
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check login
        response.setContentType("text/html");
        if (isValidUser) {
            response.getWriter().println("<html><head>");
            response.getWriter().println("<style>");
            response.getWriter().println("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
            response.getWriter().println(".center { text-align: center; }");
            response.getWriter().println(".logout { position: absolute; top: 20px; right: 20px; }");
            response.getWriter().println("</style>");
            response.getWriter().println("</head><body>");

            // Logout button at top-right
            response.getWriter().println("<div class='logout'>");
            response.getWriter().println("<form action='Logout' method='post'>");
            response.getWriter().println("<input type='submit' value='Logout'>");
            response.getWriter().println("</form>");
            response.getWriter().println("</div>");

            // Centered content
            response.getWriter().println("<div class='center'>");
            response.getWriter().println("<h2 style='color:green;'>Login Successful!</h2>");
            response.getWriter().println("<p>Welcome, " + username + ".</p>");
            response.getWriter().println("<p><b>Your Task:</b> " + task + "</p>"); // 👈 show the task here
            response.getWriter().println("</div>");

            response.getWriter().println("</body></html>");
        }
        else {
            response.getWriter().println("<html><head>");
            response.getWriter().println("<style>");
            response.getWriter().println("body { display: flex; justify-content: center; align-items: center; height: 100vh; }");
            response.getWriter().println("div { text-align: center; }");
            response.getWriter().println("</style>");
            response.getWriter().println("</head><body>");
            response.getWriter().println("<div>");
            response.getWriter().println("<h2 style='color:red;'>Invalid Username or Password.</h2>");
            response.getWriter().println("<a href='Login.jsp'>Try Again</a>");
            response.getWriter().println("</div>");
            response.getWriter().println("</body></html>");
        }
    }
}
