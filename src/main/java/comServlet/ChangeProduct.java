package comServlet;

import beans.Product;
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
 * Класс для изменения данных о существующем продукте.
 * @version 1.0
 * @autor Trusov Anton
 */
public class ChangeProduct implements Command {
    /**
     * Метод для изменения данных о существующем продукте.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String code = (String) request.getParameter("cod_product");
        String name = (String) request.getParameter("name_product");
        String priceStr = (String) request.getParameter("price");
        String massStr = (String) request.getParameter("average_mass");
        float price = 0;
        float mass = 0;
        try {
            price = Float.parseFloat(priceStr);
            mass =  Float.parseFloat(massStr);
        } catch (Exception e) {
        }
        Product product = new Product(code, name, price, mass);

        String errorString = null;

        try {
            DBUtils.updateProduct(conn, product);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        // Если имеется ошибка, forward к странице edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }
}
