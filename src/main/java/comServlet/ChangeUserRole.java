package comServlet;

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
 * Класс для изменения роли существующего пользователя.
 * @version 1.0
 * @autor Trusov Anton
 */
public class ChangeUserRole implements Command {
    /**
     * Метод для изменения роли существующей записи пользователя.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
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
        System.out.println(" cod user: " + codUserChangeString + " cod role" + codRoleString);


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
        if (errorString == null) {
            statusString = "Updating success";
            System.out.println("Update sucess user with cod " + codUserChange);
            try {
                DBUtils.commit(conn);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        response.sendRedirect(request.getContextPath() + "/accountView");
    }
}
