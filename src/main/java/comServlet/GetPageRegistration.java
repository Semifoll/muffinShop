package comServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс для перехода на страницу регистсрации нового пользователя.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageRegistration implements Command {
    /**
     * Метод для перехода на страницу регистрации нового пользователя.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/registrationUser.jsp");

        dispatcher.forward(request, response);
    }
}
