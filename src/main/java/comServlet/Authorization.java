package comServlet;

import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс для авторизации пользователя после введения данных на странице.
 * @version 1.0
 * @autor Trusov Anton
 */
public class Authorization implements Command {
    /**
     * Метод для авторизации пользователя.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if (nickName == null || password == null ||
                nickName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required nick name and password!";
        } else {
            System.out.println("StartConnect User!");
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Найти user в DB.
                user = DBUtils.findUser(conn, nickName, password);
                System.out.println("Find User!");
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setNickName(nickName);
            user.setPassword(password);

            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = context.getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице userInfo.
        else {
            System.out.println("Session User no problem!");
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);

            // Если пользователь выбирает функцию "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Наоборот, удалить Cookie
            else {
                MyUtils.deleteUserCookie(response);
            }

            // Redirect (Перенаправить) на страницу /userInfo.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
}
