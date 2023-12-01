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

@WebServlet("/employees")
public class ViewEmployeeServlet extends HttpServlet {
	private static final String query = "SELECT id,name,designation,salary FROM employees";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		
		resp.setContentType("text/html");
		
		
//		Load JDBC Driver

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection("jdbc:mysql:///StudentDB","root","root");
				PreparedStatement ps = conn.prepareStatement(query);){
			ResultSet rs = ps.executeQuery();
			pw.println("<table border='1' align='center'>");
			pw.println("<tr>");
			pw.println("<th >Employee ID</th>");
			pw.println("<th >Employee Name</th>");
			pw.println("<th >Designation</th>");
			pw.println("<th >Salary</th>");
			pw.println("<th >Edit</th>");
			pw.println("<th >Delete</th>");
			pw.println("</tr>");
			while (rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+ rs.getString(2)+"</td>");
				pw.println("<td>"+ rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getFloat(4)+"</td>");
				pw.println("<td> <a href='editScreen?id="+rs.getInt(1)+"'>Edit<a/></td>");
				pw.println("<td> <a href='deleteurl?id="+rs.getInt(1)+"'>Delete<a/></td>");
				pw.println("</tr>");
				
			}
			pw.println("</table>");
			
			
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
