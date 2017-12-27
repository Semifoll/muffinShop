package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.OrderObject;
import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Класс сервлет для предоставления списка заказов для рабочих.
 * <b>serialVersionUID</b> - константа серийной версии UID.
 *
 * @version 1.0
 * @autor Trusov Anton
 */
@WebServlet(urlPatterns = {"/orderListWorker"})
public class ListOfOrderWorkers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор класса ListOfOrderWorkers с вызовом класса-родителя.
     */
    public ListOfOrderWorkers() {
        super();
    }

    /**
     * Метод для перехвата HTTP запросов GET. Получает список из базы данных
     * и перенаправляет на страницу для представления этого списка.
     * Переменная статус отвечает за список отображаемых заказов.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);

        UserAccount user = MyUtils.getLoginedUser(session);
        // Если еще не вошел в систему (login).
        if (user == null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        //
        if (!(user.getAccessRights().equals("Admin"))) {
            if (!(user.getAccessRights().equals("Worker"))) {
                response.sendRedirect(request.getContextPath() + "/orderList");
                return;
            }
        }


        String errorString = null;
        List<OrderObject> list = null;
        String status;
        try {
            status = request.getAttribute("statusOrder").toString();
        } catch (Exception e) {
            status = "Complete";
        }
        try {
            list = DBUtils.listOrder(conn, status);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();

        }
        System.out.println("Get List of order success!");
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("orderList", list);


        //RequestDispatcher dispatcher = request.getServletContext()
        //        .getRequestDispatcher("/WEB-INF/views/orderList/orderListWorker.jsp");
        System.out.println(list.size());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/orderList/orderListWorker.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Метод для перехвата HTTP запросов POST. Ставит переменную статус из страницы к запросу.
     * Переменная статус отвечает за список отображаемых заказов.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Проверить, вошел ли пользователь в систему (login) или нет.
        HttpSession session = request.getSession();
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
        String status;
        try {
            status = request.getParameter("statusOrder");
            if (status == null) {
                status = "Complete";
            }
        } catch (Exception e) {
            status = "Complete";
        }
        request.setAttribute("statusOrder", status);
        // Если еще не вошел в систему (login).
        doGet(request, response);
    }

}