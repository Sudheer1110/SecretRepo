package DAOPack;

import db_pack.usersDB;

public class DAOFactory {
	private static usersDB udb = null;

	public static UserInt getUserInt() {
		if (udb == null) {
			udb = new usersDB();
		}
		return udb;
	}
}
