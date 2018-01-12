package comServlet;

import beans.UserAccount;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Класс для перехода на страницу авторизации пользователя.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageLogin implements Command {
    /**
     * Метод для перехода на страницу авторизации пользователя.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Проверить, вошел ли пользователь в систему (login) или нет.
        UserAccount loginedUser = MyUtils.getLoginedUser(session);

        // Если еще не вошел в систему (login).
        if (loginedUser != null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/userInfo");
            return;
        }
        // Forward (перенаправить) к странице /WEB-INF/views/loginView.jsp
        // (Пользователь не может прямо получить доступ
        // к страницам JSP расположенные в папке WEB-INF).
        RequestDispatcher dispatcher //
                = context.getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        System.out.println("Get Result dispacher!");
        dispatcher.forward(request, response);
    }
}
