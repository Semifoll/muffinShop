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
 * Класс сервлет для представления страницы информации о пользователе.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageUserInfo implements Command {
    /**
     * Направляет на страницу информации о пользователе.
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
        if (loginedUser == null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).
        request.setAttribute("user", loginedUser);

        // Если пользователь уже вошел в систему (login), то forward (перенаправить) к странице
        // /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher //
                = context.getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
    }
}
