package comServlet;

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
        final Logger fileLogger = LogManager.getLogger("ChangeRoleLogger");
        final Logger consolLogger = LogManager.getLogger();
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
            consolLogger.warn("Error with parse integer to str from change User role. ");
            request.setAttribute("errorString", errorString);
            InvokerServlet.commandsList.get("errorPage").execute(request,response,context);
            return;
        }

        try {
            DBUtils.updateUserRole(conn, codUserChange, codRole);
            fileLogger.info(
                    "Role update! cod user = " + codUserChangeString + ", new Role = " + codRoleString);
        } catch (SQLException e) {
            errorString = e.getMessage();
            request.setAttribute("errorString", errorString);
            InvokerServlet.commandsList.get("errorPage").execute(request,response,context);
            return;
            //RequestDispatcher dispatcher = request.getServletContext()
            //        .getRequestDispatcher("/WEB-INF/views/errorPage.jsp");
            //dispatcher.forward(request, response);
        }
        if (errorString == null) {
            consolLogger.info("Update sucess user with cod " + codUserChange);
            try {
                DBUtils.commit(conn);
            } catch (SQLException e) {
                errorString = e.getMessage();
                request.setAttribute("errorString", errorString);
                InvokerServlet.commandsList.get("errorPage").execute(request,response,context);
                return;
            }
        }
        //response.sendRedirect(request.getContextPath() + "/accountView");
        InvokerServlet.commandsList.get("pageAccountView").execute(request,response,context);
        return;
    }
}
