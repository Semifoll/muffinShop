
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

        import beans.Product;
        import beans.UserAccount;
        import utils.DBUtils;
        import utils.MyUtils;

@WebServlet(urlPatterns = { "/buyProduct" })
public class BuyProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BuyProduct() {
        super();
    }

    // Отобразить страницу редактирования продукта.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String code = (String) request.getParameter("code");

        Product product = null;

        String errorString = null;

        HttpSession session = request.getSession();
        UserAccount user  = MyUtils.getLoginedUser(session);

        try {
            product = DBUtils.findProduct(conn, code);
            System.out.println("product "+ product.getName());
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}