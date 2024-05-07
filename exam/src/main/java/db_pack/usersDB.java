package db_pack;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import DAOPack.UserInt;
import model_pack.user;
import model_pack.userPassword;

public class usersDB implements UserInt {

	private Connection cn = null;

	public usersDB() {
		this.cn = DBConn.getConnection();
	}

	public void createUser(user u) {
		try {
			CallableStatement st = cn.prepareCall("call createUser(?,?,?,?)");
			st.setString(1, u.getUsername());
			st.setString(2, u.getFullname());
			st.setString(3, u.getUsermail());
			st.setLong(4, u.getUsercontactnumber());
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getUserId(String username) {
		try {
			PreparedStatement st = cn.prepareStatement("select getUserId(?)");
			st.setString(1, username);

			ResultSet rst = st.executeQuery();
			if (rst.next()) {
				return rst.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void createUserpwd(int userid, String userpwd) {
		try {
			CallableStatement st = cn.prepareCall("call createUserPassword(?,?)");
			st.setInt(1, userid);
			st.setString(2, userpwd);
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkUserIdExists(int id) {
		try {
			CallableStatement st = cn.prepareCall("{?=call checkUserIdExists(?)}");
			st.registerOutParameter(1, Types.BOOLEAN);
			st.setInt(2, id);
			st.execute();
			return st.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkUserNameExists(String username) {
		try {
			CallableStatement st = cn.prepareCall("{?=call checkUserNameExists(?)}");
			st.registerOutParameter(1, Types.BOOLEAN);
			st.setString(2, username);
			st.execute();
			return st.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkUserMailExists(String usermail) {
		try {
			CallableStatement st = cn.prepareCall("{?=call checkUserMailExists(?)}");
			st.registerOutParameter(1, Types.BOOLEAN);
			st.setString(2, usermail);
			st.execute();
			return st.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkUserContactExists(long usercontactnumber) {
		try {
			CallableStatement st = cn.prepareCall("{?=call checkUserContactExists(?)}");
			st.registerOutParameter(1, Types.BOOLEAN);
			st.setLong(2, usercontactnumber);
			st.execute();
			return st.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean authenticateUser(userPassword userpass) {
		try {
			PreparedStatement st = cn.prepareStatement("select authenticate_user(?, ?) ");

			st.setInt(1, userpass.getUserId());
			st.setString(2, userpass.getUserPassword());
			ResultSet rst = st.executeQuery();
			if (rst.next()) {
				return rst.getBoolean(1);
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUserPassword(userPassword userpass) {
		try {
			CallableStatement st = cn.prepareCall("{ ? = call update_user_password(?, ?) }");

			st.registerOutParameter(1, Types.BOOLEAN);
			st.setInt(2, userpass.getUserId());
			st.setString(3, userpass.getUserPassword());
			st.execute();

			return st.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
