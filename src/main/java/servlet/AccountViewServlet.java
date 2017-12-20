
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
        import beans.Role;
        import beans.UserAccount;
        import utils.DBUtils;
        import utils.MyUtils;

@WebServlet(urlPatterns = { "/accountView" })
public class AccountViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AccountViewServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();


        RequestDispatcher dispatcher;

        String errorString = null;
        List<Role> listRole = null;
        try {
            listRole = DBUtils.queryRole(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        System.out.println("Get List of role success!");
        // Сохранить информацию в request attribute перед тем как forward к views.



        request.setAttribute("roleList", listRole);

        List<UserAccount> listUser = null;
        try {
            listUser = DBUtils.queryUsersAccounts(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString =errorString +" "+ e.getMessage();
        }
        request.setAttribute("usersList", listUser);

        dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/accountsView.jsp");

        // Forward к /WEB-INF/views/productListView.jsp
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).

        request.setAttribute("errorString", errorString);

        System.out.println("List User size ="+listUser.size());
        System.out.println("List Role size ="+listRole.size());
        dispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}