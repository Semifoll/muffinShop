package filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
 
import conn.ConnectionUtils;
import utils.MyUtils;

/**
 * Класс служит для проверки запроса на наличие соответствующего сервлета.
 * @version 1.0
 * @autor Trusov Anton
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {
    /**
     * Конструктор класса JDBCFilter с вызовом класса-родителя.
     */
    public JDBCFilter() {
    }

    /**
     * Перегрузка функции init для Filter
     * @param fConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
 
    }

    /**
     * Перегрузка функции destroy для Filter
     */
    @Override
    public void destroy() {
 
    }
 
    // Проверить является ли Servlet цель текущего request?

    /**
     * Функция для проверки запроса на возможность выполнения в перечне сервлетов.
     * @param request
     * @return
     */
    private boolean needJDBC(HttpServletRequest request) {
        System.out.println("JDBC Filter");
        // 
        // Servlet Url-pattern: /spath/*
        // 
        // => /spath
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String pathInfo = request.getPathInfo();
 
        String urlPattern = servletPath;
 
        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
        }
 
        // Key: servletName.
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();
 
        // Коллекционировать все Servlet в вашем WebApp.
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Функция для фильтрования потока запросов для открытия подключений к базе данных.
     * Настройка автокоммита(выключен). Выполнение подключения и запроса, закрытие подключения.
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
 
        // Открыть  connection (соединение) только для request со специальной ссылкой.
        // (Например ссылка к servlet, jsp, ..)
        // Избегать открытия Connection для обычных запросов.
        // (Например image, css, javascript,... )
        if (this.needJDBC(req)) {
 
            System.out.println("Open Connection for: " + req.getServletPath());
 
            Connection conn = null;
            try {
                // Создать объект Connection подключенный к database.
                conn = ConnectionUtils.getConnection();
                // Настроить автоматический commit false, чтобы активно контролировать.
                conn.setAutoCommit(false);
 
                // Сохранить объект Connection в attribute в request.
                MyUtils.storeConnection(request, conn);
 
                // Разрешить request продвигаться далее.
                // (Далее к следующему Filter tiếp или к цели).
                chain.doFilter(request, response);
 
                // Вызвать метод commit() чтобы завершить транзакцию с DB.
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                ConnectionUtils.rollbackQuietly(conn);
                throw new ServletException();
            } finally {
                ConnectionUtils.closeQuietly(conn);
            }
        }
        // Для обычных request (image,css,html,..)
        // не нужно открывать connection.
        else {
            // Разрешить request продвигаться далее.
            // (Далее к следующему Filter tiếp или к цели).
            chain.doFilter(request, response);
        }
 
    }
 
}
