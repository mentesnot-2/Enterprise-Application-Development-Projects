package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditEmployeeServlet extends HttpServlet {
	private static final String query = "SELECT name,designation,salary FROM employees WHERE id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		
		resp.setContentType("text/html");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
//		Load JDBC Driver

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection("jdbc:mysql:///StudentDB","root","root");
				PreparedStatement ps = conn.prepareStatement(query);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<form action='editurl?id="+id+"' method='post'");
			pw.println("<table align='center'>");
			pw.println("<tr>");
			pw.println("<td>Employee Name</td>");
			pw.println("<td> <input type='text' name='name' value='"+rs.getString(1)+"'> </td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Designation</td>");
			pw.println("<td> <input type='text' name='designation' value='"+rs.getString(2)+"'> </td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Salary</td>");
			pw.println("<td> <input type='number' name='salary' value='"+rs.getFloat(3)+"'> </td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td> <input type='submit' value='Edit Employee'> </td>");
			pw.println("<td> <input type='reset' value='Cancel'> </td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
			
			
			
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h2>" + se.getMessage() + "</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h2>" + e.getMessage() + "</h2>");
;		}
		pw.println("<a href='index.html' align='center'class='bg-primary'>Home</a>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
