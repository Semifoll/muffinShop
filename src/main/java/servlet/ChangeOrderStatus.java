
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import utils.DBUtils;
import utils.MyUtils;

/**
 * Класс сервлет для изменения статуса заказа работником.
 * <b>serialVersionUID</b> - константа серийной версии UID.
 * @version 1.0
 * @autor Trusov Anton
 */
@WebServlet(urlPatterns = {"/changeOrderStatus"})
public class ChangeOrderStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор класса BuyProduct с вызовом класса-родителя.
     */
    public ChangeOrderStatus() {
        super();
    }

    /**
     * Метод для перехвата HTTP запросов GET. Производит отправку на страницу заказов для работника.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/orderListWorker.jsp");
        dispatcher.forward(request, response);

    }

    // Когда пользователь вводит userName & password, и нажимает Submit.
    // Этот метод будет выполнен.

    /**
     * Метод для перехвата HTTP запросов POST. Производит обновление статуса заказа
     * через базу данных с помощью данных переданых в запросе.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        //request.setAttribute("errorString", errorString);
        //RequestDispatcher dispatcher = request.getServletContext()
        //        .getRequestDispatcher("/WEB-INF/views/accountsView.jsp");
        //dispatcher.forward(request, response);
    }

}
