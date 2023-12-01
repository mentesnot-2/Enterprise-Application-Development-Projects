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

@WebServlet("/editurl")
public class EditEmpServlet extends HttpServlet {
	private static final String query = "UPDATE employees set name=?,designation=?,salary=? WHERE id=?";
	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		
		resp.setContentType("text/html");
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String designation = req.getParameter("designation");
		float salary = Float.parseFloat(req.getParameter("salary"));
		
//		Load JDBC Driver

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection("jdbc:mysql:///StudentDB","root","root");
				PreparedStatement ps = conn.prepareStatement(query);){
			ps.setString(1,name);
			ps.setString(2, designation);
			ps.setFloat(3, salary);
			ps.setInt(4, id);
			int count = ps.executeUpdate();
			if (count == 1) {
				pw.println("<h2>Updated Successfully</h2>");
			}else {
				pw.println("<h2>Not Updated</h2>");
			}
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h2>" + se.getMessage() + "</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h2>" + e.getMessage() + "</h2>");
;		}
		pw.println("<a href='index.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='employees'>View Employees</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
