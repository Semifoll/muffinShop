package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

/**
 * Класс служит для предоставления функций манипуляции с подключением.
 * <b>OracleConnUtils</b> -  ссылка на экземпляр данного класса. </br>
 * @version 1.0
 * @autor Trusov Anton
 */
public class OracleConnUtils {
    /**
     * Свойство - имя хоста базы.
     */
    private final String hostName; // "localhost";
    /**
     * Свойство - имя сида
     */
    private final String sid;
    /**
     * Свойство - имя пользователя подключения к БД.
     */
    private final String userName;
    /**
     * Свойство - пароль пользователя подключения к БД.
     */
    private final String password;
    /**
     * Свойство - ссылка на экземпляр данного класса.
     */
    public static OracleConnUtils ORACLE_CONNECTION_UTILS;

    /**
     * Закрытый конструктор класса ORACLECONNUTILS.
     * Создает объект через чтение property файла "settings.ini" из папки resources/config.
     */
    private OracleConnUtils() {
        Properties props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("config/settings.ini"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        hostName = props.getProperty("hostName");
        sid = props.getProperty("sid");
        userName = props.getProperty("userName");
        password = props.getProperty("password");

        ORACLE_CONNECTION_UTILS = this;
    }

    /**
     * Статический метод для получения ссылки на объект подключение.
     *
     * @return подключение к оракл базе.
     */
    public static OracleConnUtils getInstance() {
        if (ORACLE_CONNECTION_UTILS == null) {
            OracleConnUtils but = new OracleConnUtils();
            ORACLE_CONNECTION_UTILS = but;
        }
        return ORACLE_CONNECTION_UTILS;
    }

    /**
     * Функция для изменения параметров соединения с базой данных.
     *
     * @return Возвращает ссылку на подключение к базе данных Oracle.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getOracleConnection() throws ClassNotFoundException, SQLException {

        // Примечание: Изменить параметры соединения соответствующе.

        return getOracleConnection(
                OracleConnUtils.getInstance().hostName,
                OracleConnUtils.getInstance().sid,
                OracleConnUtils.getInstance().userName,
                OracleConnUtils.getInstance().password);
    }

    /**
     * Функция для изменения параметров соединения с базой данных.
     *
     * @return Возвращает ссылку на подключение к базе данных Oracle.
     */
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