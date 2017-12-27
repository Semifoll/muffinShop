package servlet;

import beans.UserAccount;
import utils.MyUtils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Класс сервлет для перенаправления на страницу информации о пользователе.
 * <b>serialVersionUID</b> - константа серийной версии UID.
 * @version 1.0
 * @autor Trusov Anton
 */
@WebServlet(urlPatterns = { "/info"})
public class InfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * Конструктор класса InfoServlet с вызовом класса-родителя.
     */
    public InfoServlet() {
        super();
    }
    /**
     * Метод для перехвата HTTP запросов GET. Перенаправляет на страницу информации.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Forward to /WEB-INF/views/homeView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/infoPage.jsp");

        dispatcher.forward(request, response);

    }
    /**
     * Метод для перехвата HTTP запросов POST.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}