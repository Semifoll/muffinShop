package comServlet;

import beans.Product;
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
 * Класс для перехода на страницу просмотра продуктов.
 * @version 1.0
 * @autor Trusov Anton
 */
public class GetPageProducts implements Command {
    /**
     * Метод для перехода на страницу просмотра продуктов.
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

        RequestDispatcher dispatcher;

        String errorString = null;
        List<Product> list = null;
        try {
            list = DBUtils.queryProduct(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
            consolLogger.error("Error. Problem with query product in page product. ");
        }
        // Сохранить информацию в request attribute перед тем как forward к views.

        request.setAttribute("errorString", errorString);

        request.setAttribute("productList", list);

        UserAccount curUser = MyUtils.getLoginedUser(request.getSession());
        String accessRights;

        if(curUser == null){
            //отправить к списку без заказа
            accessRights = "no rights";
        }else{
            accessRights = curUser.getAccessRights();
        }
        request.setAttribute("user", curUser);
        switch (accessRights) {
            case "Admin":
                //go to aProductList;
                dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/productList/aProductList.jsp");

                break;
            case "Worker":
                //go to wProductList;
                dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/productList/aProductList.jsp");

                break;
            case "Client":
                // go to cProductList;
                dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/productList/cProductList.jsp");

                break;
            default:
                //go to def product list not logged;
                dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/productList/productListView.jsp");

                break;
        }
        // Forward к /WEB-INF/views/productListView.jsp
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).
        request.setAttribute("user", curUser);
        dispatcher.forward(request, response);

    }
}
