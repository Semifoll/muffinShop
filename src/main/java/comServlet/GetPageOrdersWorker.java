package comServlet;

import beans.OrderObject;
import beans.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Класс для перехода на страницу заказов всех пользователей. Данная страница предназначена для работы сотрудников.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageOrdersWorker implements Command {
    /**
     * Метод для перехода на страницу заказов всех пользователей.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {

        final Logger consolLogger = LogManager.getLogger();
        Connection conn = MyUtils.getStoredConnection(request);

        UserAccount user = MyUtils.getLoginedUser(request.getSession());
        // Если еще не вошел в систему (login).
        if (user == null) {
            // Redirect (Перенаправить) к странице login.
            //response.sendRedirect(request.getContextPath() + "/login");
            InvokerServlet.commandsList.get("loginPage").execute(request,response,context);
            return;
        }
        //
        if (!(user.getAccessRights().equals("Admin"))) {
            if (!(user.getAccessRights().equals("Worker"))) {
                //response.sendRedirect(request.getContextPath() + "/orderList");
                InvokerServlet.commandsList.get("clientOrdersPage").execute(request,response,context);
                return;
            }
        }


        String errorString = null;
        List<OrderObject> list = null;
        String status;
        try {
            status = request.getParameter("statusOrder").toString();
        } catch (Exception e) {
            status = "Complete";
        }
        try {
            list = DBUtils.listOrder(conn, status);
        } catch (SQLException e) {
            e.printStackTrace();
            consolLogger.error("Error. Problem with listOrder in order workers.");
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("orderList", list);


        //RequestDispatcher dispatcher = request.getServletContext()
        //        .getRequestDispatcher("/WEB-INF/views/orderList/orderListWorker.jsp");
        RequestDispatcher dispatcher = context.
                getRequestDispatcher("/WEB-INF/views/orderList/orderListWorker.jsp");

        dispatcher.forward(request, response);
    }
}
