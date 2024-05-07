package control_pack;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import DAOPack.DAOFactory;
import DAOPack.UserInt;
import model_pack.userPassword;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/resetPass")
public class updatePasswordServlet extends HttpServlet {
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		rs.setContentType("application/json");
		UserInt udb = DAOFactory.getUserInt();
		JSONObject jsobj = new JSONObject();

		String username = rq.getParameter("username");
		int userid = udb.getUserId(username);
		String password = rq.getParameter("password");

		userPassword userpass = new userPassword(userid, password);

		boolean status = udb.updateUserPassword(userpass);
		if (status) {
			jsobj.put("user_message", "Successfully Password was Reset");
		} else {
			jsobj.put("user_message", "Username do not exists");
		}
		rs.getWriter().write(jsobj.toString());
	}
}