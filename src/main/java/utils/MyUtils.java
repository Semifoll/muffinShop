package utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import beans.UserAccount;
/**
 * Класс для утилит работы с БД.
 * <b>ATT_NAME_CONNECTION</b> - атрибуты для подключения.
 * <b>ATT_NAME_USER_NAME</b> - атрибуты для данных пользователя из кукки.
 * @version 1.0
 * @autor Trusov Anton
 */
public class MyUtils {
    /** Свойство - атрибуты для подключения*/
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    /** Свойство - атрибуты для данных пользователя из кукки*/
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
 
    // Сохранить Connection в attribute в request.
    // Данная информация хранения существует только во время запроса (request)
    // до тех пор, пока данные возвращаются приложению пользователя.

    /**
     * Метод для сохранения данных подключения в константу из запроса.
     * @param request - запрос.
     * @param conn - ссылка на подключение к БД.
     */
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }

    /**
     * Метод для получения данных подключения из константы.
     * @param request
     * @return
     */
    // Получить объект Connection сохраненный в attribute в request.
    public static Connection getStoredConnection(ServletRequest request) {
        return (Connection) request.getAttribute(ATT_NAME_CONNECTION);
    }

    /**
     * Метод для сохранения информации пользователя который вощел в систему( Login) в Session.
     * @param session - сессия.
     * @param loginedUser - авторизированный пользователь.
     */
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        // В JSP можно получить доступ через ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    /**
     * Метод для получения информации пользователя, сохраненная в Session.
     * @param session - сессия.
     * @return - возращает информацию пользователя.
     */
    public static UserAccount getLoginedUser(HttpSession session) {
        return (UserAccount) session.getAttribute("loginedUser");
    }

    /**
     * Метод для сохранения информации пользователя в кукки.
     * @param response - ответ.
     * @param user - данные о пользователе.
     */
    public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getNickName());
        // 1 день (Конвертированный в секунды)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    /**
     * Метод для получения имени пользователя из сохраненных данных в кукки.
     * @param request - запрос.
     * @return - возращает имя пользователя.
     */
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Метод для удаления данных из кукки.
     * @param response - ответ.
     */
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 секунд. (Данный Cookie будет сразу недействителен)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
 
}