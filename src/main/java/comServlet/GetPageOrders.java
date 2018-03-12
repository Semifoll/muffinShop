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
 * Класс для перехода на страницу заказов зарегистрированным пользователем.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageOrders implements Command {
    /**
     * Метод для перехода на страницу заказов зарегистрированным пользователем.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);
        final Logger consolLogger = LogManager.getLogger();

        UserAccount user  = MyUtils.getLoginedUser(request.getSession());
        // Если еще не вошел в систему (login).
        if (user == null) {
            // Redirect (Перенаправить) к странице login.
            //response.sendRedirect(request.getContextPath() + "/login");
            InvokerServlet.commandsList.get("loginPage").execute(request,response,context);
            return;
        }
        //
        //Перенаправление на страницу работы с заказами.
        // Проверить, вошел ли пользователь в систему (login) или нет.

        // Если еще не вошел в систему (login).
        if (user.getAccessRights().equals("Admin")||
                user.getAccessRights().equals("Worker")) {
            //Redirect (Перенаправить) к странице wOrders
            InvokerServlet.commandsList.get("wOrders").execute(request,response,context);
            return;
        }
        //

        String errorString = null;
        List<OrderObject> list = null;
        String status;
        try {
                status = request.getParameter("status").toString();
        } catch (Exception e) {
            status = "Complete";
        }
        try {
            list = DBUtils.listOrder(conn,user,status);
        } catch (SQLException e) {
            e.printStackTrace();
            consolLogger.error("Error. Problem with listOrder from user:" + user.getCod()+" and status:"+status);
            errorString = e.getMessage();

        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("orderList", list);
        request.setAttribute("userNickName", user.getNickName());

        // Forward к /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/orderList/orderList.jsp");
        dispatcher.forward(request, response);

    }
}
