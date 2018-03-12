package comServlet;

import beans.UserAccount;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Класс для перехода на страницу изменения данных продукта.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageCreateProduct implements Command {
    /**
     * Метод для перехода на страницу изменения данных продукта.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {

        UserAccount curUser = MyUtils.getLoginedUser(request.getSession());
        RequestDispatcher dispatcher;
        if(curUser.getAccessRights()!= "Admin"){
            dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/homeView.jsp");
        }else{
            dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
        }

        dispatcher.forward(request, response);
    }
}

