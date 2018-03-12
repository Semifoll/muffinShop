package comServlet;

import beans.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.text.ParseException;

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
        final Logger consolLogger = LogManager.getLogger();

        String code = (String) request.getParameter("code");
        String name = (String) request.getParameter("name");
        String priceStr = (String) request.getParameter("price");
        String massStr = (String) request.getParameter("mass");
        float price = 0;
        float mass = 0;
        try {
            price = Float.parseFloat(priceStr);
            mass =  Float.parseFloat(massStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorString", e.getMessage());
            consolLogger.warn("Error with parse integer to str from change product. ");
            InvokerServlet.commandsList.get("errorPage").execute(request,response,context);
            return;
        }
        try {
            DBUtils.updateProduct(conn, code, name, price, mass);
            consolLogger.info("Product has been changed: product : " +
                    "cod"+ code+", name"+ ", price"+priceStr+", mass"+massStr+". ");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorString", e.getMessage());
            consolLogger.warn("Error with update Product values: " +
                            "cod"+ code+", name"+ ", price"+priceStr+", mass"+massStr+". ");
            InvokerServlet.commandsList.get("wOrders").execute(request,response,context);
        }
        return;
    }
}
