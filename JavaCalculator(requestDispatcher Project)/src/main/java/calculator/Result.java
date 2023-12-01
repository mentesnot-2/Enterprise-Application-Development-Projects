package calculator;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/showResult")
public class Result extends HttpServlet {
	protected void doPost(HttpServletRequest req,
			HttpServletResponse res) throws IOException{

			Double result = (Double) req.getAttribute("result");
			PrintWriter out = res.getWriter();
			out.print("<h1>your result is " + result + "</h1>");
			out.println("<div>"
					+ "<a href=\"index.html\">Return</a>"
					+ "</div>");
	}
}
