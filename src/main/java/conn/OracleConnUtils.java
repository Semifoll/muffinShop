package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class OracleConnUtils {

	public static Connection getOracleConnection() throws ClassNotFoundException, SQLException {

		// Примечание: Изменить параметры соединения соответствующе.
		String hostName = "DESKTOP-T67DR6S"; // "localhost";
		String sid = "XE";
		String userName = "tempAdmin";
		String password = "12341";

		return getOracleConnection(hostName, sid, userName, password);
	}

	public static Connection getOracleConnection(String hostName, String sid, String userName, String password) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		// Структура URL Connection для Oracle
		// Например:
		// jdbc:oracle:thin:@localhost:1521:db11g
		// jdbc:oracle:thin:@//HOSTNAME:PORT/SERVICENAME
		String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;
		Locale.setDefault(Locale.ENGLISH);
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		System.out.println("||||||||||||||||||");
		System.out.println("connect is success");
		System.out.println("||||||||||||||||||");
		return conn;
	}
}