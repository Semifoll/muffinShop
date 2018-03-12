package comServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * Класс для изменения статуса заказа.
 * @version 1.0
 * @autor Trusov Anton
 */
public class ChangeOrderStatus implements Command {
    /**
     * Метод для изменения статуса заказа.
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

        String codOrder = request.getParameter("codOrder");
        String newStatus = request.getParameter("statusOrder");
        int codOrderChange;

        String errorString = null;
        try {
            codOrderChange = Integer.parseUnsignedInt(codOrder);

        } catch (Exception e) {
            //экстренный выход
            errorString = e.getMessage();
            request.setAttribute("errorString", errorString);
            InvokerServlet.commandsList.get("errorPage").execute(request,response,context);
            return;
        }
        try {
            DBUtils.updateOrderStatus(conn, codOrderChange, newStatus);
        } catch (SQLException e) {
            errorString = e.getMessage();
            request.setAttribute("errorString", errorString);
            InvokerServlet.commandsList.get("errorPage").execute(request,response,context);
            return;
        }
        if (errorString == null) {
            consolLogger.info("Update sucess user with cod " + codOrderChange + " on " + newStatus);
            try {
                DBUtils.commit(conn);
            } catch (SQLException e) {
                consolLogger.info(e);
            }
        }
        InvokerServlet.commandsList.get("wOrders").execute(request,response,context);
        //response.sendRedirect(request.getContextPath() + "/orderListWorker");

    }
}
