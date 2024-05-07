package control_pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import DAOPack.DAOFactory;
import DAOPack.UserInt;
import model_pack.user;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/signup")
public class signupServlet extends HttpServlet {
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		try {
			rs.setContentType("application/json");

			UserInt udb = DAOFactory.getUserInt();
			JSONObject jsobj = new JSONObject();
			PrintWriter pw = rs.getWriter();

			String username = rq.getParameter("username");
			if (udb.checkUserNameExists(username)) {
				rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				jsobj.put("error", "username already exists");
				pw.write(jsobj.toString());
				return;
			}

			String useremail = rq.getParameter("useremail");
			if (udb.checkUserMailExists(useremail)) {
				rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				jsobj.put("error", "usermail already exists");
				pw.write(jsobj.toString());
				return;
			}

			Long usercontactnumber = Long.parseLong(rq.getParameter("usercontactnumber"));
			if (udb.checkUserContactExists(usercontactnumber)) {
				rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				jsobj.put("error", "userphonenumber already exists");
				pw.write(jsobj.toString());
				return;
			}

			user u = new user(1, username, rq.getParameter("userfullname"), useremail, usercontactnumber);
			udb.createUser(u);

			int userid = udb.getUserId(username);
			String userpwd = rq.getParameter("userpassword");

			udb.createUserpwd(userid, userpwd);

			jsobj.put("success", "usercreated login now");
			pw.write(jsobj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}