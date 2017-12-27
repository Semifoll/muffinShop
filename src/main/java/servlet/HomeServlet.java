package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Класс сервлет для перенаправления на домашнюю страницу.
 * <b>serialVersionUID</b> - константа серийной версии UID.
 * @version 1.0
 * @autor Trusov Anton
 */
@WebServlet(urlPatterns = { "/home"})
public class HomeServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

    /**
     * Конструктор класса HomeServlet с вызовом класса-родителя.
     */
   public HomeServlet() {
       super();
   }

    /**
     * Метод для перехвата HTTP запросов GET. Перенаправляет на домашнюю страницу.
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
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
        
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