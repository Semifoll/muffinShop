package comServlet;

import utils.Finals;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Мастер класс сервлет. Осуществляет вызов оброботчиков по URL
 * @version 1.0
 * @autor Trusov Anton
 */
public class InvokerServlet extends HttpServlet {
    /**
     * Свойство - множество где: индекс(URL), значение(ссылка на обработчик).
     */
    static Finals finals = new Finals();

    /**
     * Статическое поле инициализации для
     */

    /**
     * Метод для перехвата GET запросов. Отвечает за распознование URL и запуск соответствующего обработчика.
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        // Servlet Url-pattern: /spath/*
        //
        // => /spath
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String pathInfo = request.getPathInfo();

        String urlPattern = servletPath;
        Command page;
        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
        }
        page = finals.commandsList.get(urlPattern);

        if(urlPattern.equals("/authorization")){
            System.out.println("authorization");
        }
        try {
            page.execute(request,response,this.getServletContext());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request,response);
    }

}
