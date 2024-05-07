package DAOPack;

import model_pack.user;
import model_pack.userPassword;

public interface UserInt {
	public boolean updateUserPassword(userPassword userpass);

	public boolean authenticateUser(userPassword userpass);

	public boolean checkUserContactExists(long usercontactnumber);

	public boolean checkUserMailExists(String usermail);

	public boolean checkUserNameExists(String username);

	public int getUserId(String username);

	public void createUser(user u);

	public void createUserpwd(int userid, String userpwd);

	public boolean checkUserIdExists(int id);

}
