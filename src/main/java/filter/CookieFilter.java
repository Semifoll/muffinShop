package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;
/**
 * Класс служит для фильтрации потоков.
 * @version 1.0
 * @autor Trusov Anton
 */
//@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {
    /** Конструктор класса CookieFiler
     *
     */
    public CookieFilter() {
    }
    /** Переопределение функции init для Filter с параметром:
     * @param fConfig - конфигурация фильтра.
     * @throws SQLException
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
 
    }
    /** Переопределение функции destroy для Filter
     */
    @Override
    public void destroy() {
 
    }
    /** Переопределение функции doFilter для Filter. Фактическое выполнение фильтрации потока запросов.
     * Проверка на наличие куки-файлов. Параметры:
     * @param request - запрос пришедший на сервлет.
     * @param response - ответ отправляющий от сервлета.
     * @param chain - цепь фильтров.
     * @throws IOException
     * @throws ServletException
     *
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Cookie Filter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
 
        UserAccount userInSession = MyUtils.getLoginedUser(session);
        // 
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }
 
        // Connection создан в JDBCFilter.
        Connection conn = MyUtils.getStoredConnection(request);
 
        // Флаг(flag) для проверки Cookie.
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            String userName = MyUtils.getUserNameInCookie(req);
            try {
                UserAccount user = DBUtils.findUser(conn, userName);
                MyUtils.storeLoginedUser(session, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Отметить проверенные Cookie.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
 
        chain.doFilter(request, response);
    }
 
}