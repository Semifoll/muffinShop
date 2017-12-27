package conn;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * Класс служит для предоставления функций манипуляции с подключением.
 * @version 1.0
 * @autor Trusov Anton
 */
public class ConnectionUtils {
	/** Функция для подключения к базе данных Oracle
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return Возвращает ссылку на подключение.
	 *
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		// Здесь я подключаюсь к Oracle Database.
		// (Вы можете поменять и использовать другую базу данных).
		return OracleConnUtils.getOracleConnection();

		// return OracleConnUtils.getOracleConnection();
		// return MySQLConnUtils.getMySQLConnection();
		// return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
		// return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
		// return PostGresConnUtils.getPostGresConnection();
	}
	/** Функция для закрытия подключения к базе данных.
	 */
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}
	/** Функция для отката изменений в базе данных.
	 */
	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
		}
	}
}