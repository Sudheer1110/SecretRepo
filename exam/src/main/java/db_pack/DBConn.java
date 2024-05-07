package db_pack;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConn {
	public static Connection conn;

	public static Connection getConnection() {
		try {
			if (conn == null) {
				Class.forName("org.postgresql.Driver");

				Properties p = new Properties();
				FileReader f = new FileReader("D:\\workspace\\Pro\\authentication\\prop.properties");
				p.load(f);

				conn = DriverManager.getConnection(p.getProperty("jdbc_conn_string"), p.getProperty("user"),
						p.getProperty("password"));
				System.out.println("Connection established");
			} else {
				System.out.println("Returning existing Connection");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void destroyConnection() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Connection Closed and Invalidated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
