package onlineRegistrationSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
	private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			String query = "SELECT * FROM users WHERE email=? AND password=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//authentication successful
				String userName = rs.getString("name");
				HttpSession session = req.getSession();
				
				session.setAttribute("username", userName);
				conn.close();
				res.sendRedirect("welcome.jsp");
			}else {
				//authentication failed
				conn.close();
				res.sendRedirect("login.jsp");
				}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
