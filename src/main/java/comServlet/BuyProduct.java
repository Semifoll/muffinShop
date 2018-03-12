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
 * Класс для перехода на страницу заказа продуктов.
 * @version 1.0
 * @autor Trusov Anton
 */
public class BuyProduct implements Command {
    /**
     * Метод для перехода на страницу заказа продуктов.
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

        String code = (String) request.getParameter("code");

        Product product = null;

        String errorString = null;

        UserAccount user = MyUtils.getLoginedUser(request.getSession());

        try {
            product = DBUtils.findProduct(conn, code);
        } catch (SQLException e) {
            errorString = e.getMessage();

            consolLogger.info("Product cod not found");
            request.setAttribute("errorString", errorString);
            //response.sendRedirect(request.getServletPath() + "/cProductList");
            InvokerServlet.commandsList.get("ourProductPage").execute(request,response,context);
        }
        String resultString;
        try {
            DBUtils.buyProduct(conn, product, user);
            consolLogger.info("Product buy product cod = " + product.getCode());
            resultString = "Product add in your order list.";
        } catch (SQLException e) {
            errorString = e.getMessage();
            request.setAttribute("errorString", errorString);
            consolLogger.info(errorString);
            InvokerServlet.commandsList.get("ourProductPage").execute(request,response,context);
            return;
        }

        List<Product> list = null;
        try {
            list = DBUtils.queryProduct(conn);
        } catch (SQLException e) {
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);

        request.setAttribute("productList", list);
        request.setAttribute("resultString", resultString);
        InvokerServlet.commandsList.get("ourProductPage").execute(request,response,context);

        //response.sendRedirect(request.getContextPath() + "/productList");
        //RequestDispatcher dispatcher = request.getServletContext()
        //        .getRequestDispatcher("/WEB-INF/views/productList/cProductList.jsp");
        //dispatcher.forward(request, response);
    }
}
