package control_pack;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOPack.DAOFactory;
import DAOPack.UserInt;

@WebFilter(urlPatterns = { "", "/login", "/welcome" })
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain)
			throws IOException, ServletException {
		// check username and pwd from 2 tables available now create a session id or cookies and write back to the
		// response
		// or you can write back to header
		// forward to the welcome page

		try {
			HttpServletRequest hrq = (HttpServletRequest) rq;
			HttpSession hs = hrq.getSession(true);

			if (hs.getAttribute("user_id") == null) {
				RequestDispatcher rd = rq.getRequestDispatcher("static_content/HTMLFiles/home.html");
				rd.include(rq, rs);
				return;
			}

			UserInt udb = DAOFactory.getUserInt();

			if (!udb.checkUserIdExists(Integer.parseInt(hs.getAttribute("user_id").toString()))) {
				RequestDispatcher rd = rq.getRequestDispatcher("static_content/HTMLFiles/home.html");
				rd.include(rq, rs);
				return;
			}

			chain.doFilter(rq, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
