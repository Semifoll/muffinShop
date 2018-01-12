package comServlet;

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
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/errorPage.jsp");
            dispatcher.forward(request, response);
            return;
        }
        System.out.println(" codOrder: " + codOrder + " newStatus" + newStatus);


        String statusString = null;
        try {
            DBUtils.updateOrderStatus(conn, codOrderChange, newStatus);
        } catch (SQLException e) {
            errorString = e.getMessage();
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/errorPage.jsp");
            dispatcher.forward(request, response);
        }
        if (errorString == null) {
            statusString = "Updating success";
            System.out.println("Update sucess user with cod " + codOrderChange + " on " + newStatus);
            try {
                DBUtils.commit(conn);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        response.sendRedirect(request.getContextPath() + "/orderListWorker");
    }
}
