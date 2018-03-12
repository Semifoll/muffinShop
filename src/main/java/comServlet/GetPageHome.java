package comServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Класс для перехода на домашнюю страницу сайта.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageHome implements Command {
    /**
     * Метод для перехода на домашнюю страницу сайта.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/homeView.jsp");
        dispatcher.forward(request, response);
        return;
    }
}
