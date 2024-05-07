package control_pack;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOPack.DAOFactory;
import DAOPack.UserInt;
import model_pack.userPassword;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/UserLogin")
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
		// check username and pwd from 2 tables available now create a session id or cookies and write back to the
		// response
		// forward to the filter

		try {
			HttpServletRequest hrq = (HttpServletRequest) rq;
			HttpSession hs = hrq.getSession(true);

			UserInt udb = DAOFactory.getUserInt();

			String username = rq.getParameter("username_login");
			int userid = udb.getUserId(username);
			String userpwd = rq.getParameter("password_login");

			userPassword userPass = new userPassword(userid, userpwd);
			boolean status = udb.authenticateUser(userPass);

			// rd and json in same filter or servlet
			if (!status) {
				HttpServletResponse hrs = (HttpServletResponse) rs;
				hrs.sendRedirect("http://localhost:8081/exam/");
				return;
			}

			hs.setAttribute("user_id", userid);
			rs.sendRedirect("http://localhost:8081/exam/login");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
