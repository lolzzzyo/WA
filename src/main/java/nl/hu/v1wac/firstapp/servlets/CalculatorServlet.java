package nl.hu.v1wac.firstapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/CalculatorServlet.do")
public class CalculatorServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
	String action = req.getParameter("action");
	String number1 = req.getParameter("number1");
	String number2 = req.getParameter("number2");
	
	int number1Con = Integer.parseInt(number1);
	int number2Con = Integer.parseInt(number2);
	int answer = 0;
	String operator = "";
	
	if("+".equals(action)){
		answer = number1Con + number2Con;
		operator = "+";
	}
	
	if("-".equals(action)){
		answer = number1Con - number2Con;
		operator = "-";
	}
	
	if("/".equals(action)){
		answer = number1Con / number2Con;
		operator = "/";
	}
	
	if("*".equals(action)){
		answer = number1Con * number2Con;
		operator = "*";
	}
	
	
	PrintWriter out = resp.getWriter();
	resp.setContentType("text/html");
	
	out.println("<!DOCTYPE html>");
	out.println("<html>");
	out.println("	<title>Dynamic Example</title>");
	out.println("	<body>");
	out.println("		<h2>Answer</h2>");
	out.println("		<h2>" + number1Con + " " + operator + " " + number2Con + " = " + answer );
	out.println("	</body>");
	out.println("</html>");
	}
}