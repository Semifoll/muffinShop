package servlet;

        import java.io.IOException;
        import java.sql.Connection;
        import java.sql.SQLException;


        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;


        import utils.DBUtils;
        import utils.MyUtils;


@WebServlet(urlPatterns = { "/changeUserRole" })
public class ChangeUserRole extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangeUserRole() {
        super();
    }

    // Показать страницу Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/accountView.jsp");
        dispatcher.forward(request, response);

    }

    // Когда пользователь вводит userName & password, и нажимает Submit.
    // Этот метод будет выполнен.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String codUserChangeString = request.getParameter("codUser");
        String codRoleString = request.getParameter("role");
        int codUserChange;
        int codRole;
        String errorString = null;
        try {
            codUserChange = Integer.parseUnsignedInt(codUserChangeString);
            codRole = Integer.parseUnsignedInt(codRoleString);
        } catch (Exception e) {
            //экстренный выход
            errorString = e.getMessage();
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/accountsView.jsp");
            dispatcher.forward(request, response);
            return;
        }
        System.out.println(" cod user: "+ codUserChangeString + " cod role"+ codRoleString);



        String statusString = null;
        try {
            DBUtils.updateUserRole(conn, codUserChange, codRole);
        } catch (SQLException e) {
            errorString = e.getMessage();
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/errorPage.jsp");
            dispatcher.forward(request, response);
        }
        if(errorString == null){
            statusString = "Updating success";
            System.out.println("Update sucess user with cod "+ codUserChange);
            try {
                DBUtils.commit(conn);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        response.sendRedirect(request.getContextPath() + "/accountView");
        //request.setAttribute("errorString", errorString);
        //RequestDispatcher dispatcher = request.getServletContext()
        //        .getRequestDispatcher("/WEB-INF/views/accountsView.jsp");
        //dispatcher.forward(request, response);
    }

}
