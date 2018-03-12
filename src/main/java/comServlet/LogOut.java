package comServlet;

import beans.UserAccount;
import utils.MyUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class    LogOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        UserAccount user = null;
        MyUtils.deleteUserCookie(response);
        HttpSession session = request.getSession();
        MyUtils.storeLoginedUser(session, user);
        InvokerServlet.commandsList.get("userInfo").execute(request,response,context);
    }
}
