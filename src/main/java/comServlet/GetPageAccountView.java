package comServlet;

import beans.Role;
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
import java.util.List;
/**
 * Класс для перехода на страницу информации об зарегистрированных пользователях.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageAccountView implements Command {
    /**
     * Метод для перехода на страницу информации об зарегистрированных пользователях.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();


        RequestDispatcher dispatcher;

        String errorString = null;
        List<Role> listRole = null;
        try {
            listRole = DBUtils.queryRole(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        System.out.println("Get List of role success!");
        // Сохранить информацию в request attribute перед тем как forward к views.



        request.setAttribute("roleList", listRole);

        List<UserAccount> listUser = null;
        try {
            listUser = DBUtils.queryUsersAccounts(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString =errorString +" "+ e.getMessage();
        }
        request.setAttribute("usersList", listUser);

        dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/accountsView.jsp");

        // Forward к /WEB-INF/views/productListView.jsp
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).

        request.setAttribute("errorString", errorString);

        System.out.println("List User size ="+listUser.size());
        System.out.println("List Role size ="+listRole.size());
        dispatcher.forward(request, response);
    }
}
