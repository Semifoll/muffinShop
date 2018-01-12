package comServlet;

import beans.Product;
import beans.UserAccount;
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
public class GetPageBuyProduct implements Command {
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

        String code = (String) request.getParameter("code");

        Product product = null;

        String errorString = null;

        HttpSession session = request.getSession();
        UserAccount user = MyUtils.getLoginedUser(session);

        try {
            product = DBUtils.findProduct(conn, code);
            System.out.println("product " + product.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
            System.out.println("product cod not found");
            request.setAttribute("errorString", errorString);
            response.sendRedirect(request.getServletPath() + "/cProductList");
            return;
        }
        String resultString = " ";
        try {
            DBUtils.buyProduct(conn, product, user);
            System.out.println();
            resultString = "Product add in your order list.";
        } catch (SQLException e) {
            e.printStackTrace();
            resultString = e.getMessage();
        }


        List<Product> list = null;
        try {
            list = DBUtils.queryProduct(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        System.out.println("Get List of product success!");
        // Сохранить информацию в request attribute перед тем как forward к views.

        request.setAttribute("errorString", errorString);

        request.setAttribute("productList", list);
        request.setAttribute("resultString", resultString);

        //response.sendRedirect(request.getContextPath() + "/productList");
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/productList/cProductList.jsp");
        dispatcher.forward(request, response);
    }
}
