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
/**
 * Класс для создания записи нового продукта.
 * @version 1.0
 * @autor Trusov Anton
 */
public class CreateProduct implements Command {
    /**
     * Метод для создания новой записи в базе данных нового продукта.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        final Logger fileLogger = LogManager.getLogger("NewProductLogger");
        final Logger consolLogger = LogManager.getLogger();

        String code = (String) request.getParameter("code");
        String name = (String) request.getParameter("name");
        String priceStr = (String) request.getParameter("price");
        String massStr = (String) request.getParameter("mass");
        float price = 0;
        float mass = 0;
        try {
            price = Float.parseFloat(priceStr);
            mass = Float.parseFloat(massStr);
        } catch (Exception e) {
            request.setAttribute("errorString", e.getMessage());
            consolLogger.warn("Error with parse integer to str from create product. ");
            InvokerServlet.commandsList.get("errorPage").execute(request,response,context);
            return;
        }
        try{
            Product product = DBUtils.findProduct(conn,code);
            if(product != null){//уже есть такой.
                fileLogger.info(
                        " Warning! You change an existing product!! cod = "+ product.getCode());
                request.setAttribute("errorString", "Warning! You change an existing product!!");
                request.setAttribute("product", product);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
                dispatcher.forward(request, response);
                return;
            }
            product = new Product(code,name,price,mass);
            String errorString = null;
            // Кодом продукта является строка [a-zA-Z_0-9]
            // Имеет минимум 1 символ.
            String regex = "\\w+";
            if (code == null || !code.matches(regex)) {
                consolLogger.warn("Error. Product Code invalid! ");
                request.setAttribute("errorString", "Product Code invalid!");
                InvokerServlet.commandsList.get("createProductPage").execute(request,response,context);
                return;
            }
            DBUtils.insertProduct(conn, product);
            fileLogger.info(
                    " Add new Product: cod = "+ product.getCode()+", " +
                            "name = " + product.getName()+", mass = "+ product.getMass()+", " +
                            "price = " + product.getPrice());
            InvokerServlet.commandsList.get("pageAdminProductList").execute(request,response,context);
            return;
        }catch (SQLException e){
            request.setAttribute("errorString", e.getMessage());
            consolLogger.warn("Error. Insert new product with error.");
            InvokerServlet.commandsList.get("errorPage").execute(request,response,context);
            return;
        }
    }
}
