package control_pack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/login", "/welcome" })
public class WelcomeServlet extends HttpServlet {
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
		RequestDispatcher rd = rq.getRequestDispatcher("static_content/HTMLFiles/welcome_page.html");
		rd.include(rq, rs);
	}
}
