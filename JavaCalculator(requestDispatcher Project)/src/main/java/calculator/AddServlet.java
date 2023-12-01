package calculator;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req,
			HttpServletResponse res) throws IOException, ServletException{

			
			String expression = req.getParameter("input");
			if (expression.contains("x")) {
				String[] parameters = expression.split("x");
				Double result = (Double.parseDouble(parameters[0])) * (Double.parseDouble(parameters[1]));
				req.setAttribute("result", result);
				RequestDispatcher dispatcher = req.getRequestDispatcher("showResult");
				dispatcher.forward(req, res);
				
			}else if (expression.contains("-")) {
				String[] parameters = expression.split("\\-");
				Double result = (Double.parseDouble(parameters[0])) - (Double.parseDouble(parameters[1]));
				req.setAttribute("result", result);
				RequestDispatcher dispatcher = req.getRequestDispatcher("showResult");
				dispatcher.forward(req, res);
				
			}
			else if (expression.contains("/")) {
				String[] parameters = expression.split("\\/");
				Double result = (Double.parseDouble(parameters[0])) / (Double.parseDouble(parameters[1]));
				req.setAttribute("result", result);
				RequestDispatcher dispatcher = req.getRequestDispatcher("showResult");
				dispatcher.forward(req, res);
				
			}
			else {
				String[] parameters = expression.split("\\+");
				Double result = (Double.parseDouble(parameters[0])) + (Double.parseDouble(parameters[1]));
				req.setAttribute("result", result);
				RequestDispatcher dispatcher = req.getRequestDispatcher("showResult");
				dispatcher.forward(req, res);
			}
			
	}
}
