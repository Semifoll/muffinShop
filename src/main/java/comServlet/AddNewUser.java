package comServlet;

import beans.UserAccount;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Класс для представления страницы регистрании нового пользователя.
 * @version 1.0
 * @autor Trusov Anton
 */
public class AddNewUser implements Command{
    /**
     * Метод для добавления нового пользователя.
     * @param request
     * @param response
     * @param context
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {

        final Logger fileLogger = LogManager.getLogger("NewUserLogger");
        final Logger consolLogger = LogManager.getLogger();
        Connection conn = MyUtils.getStoredConnection(request);
        String nickName = request.getParameter("nickName");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String password = request.getParameter("password");

        String[] birthDate = request.getParameterValues("birthDate");
        String phoneNum = request.getParameter("phoneNum");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = dateFormat.parse(birthDate[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;


        //проверка на пустоту данных
        if (nickName == null || password == null || firstName == null || secondName == null || phoneNum == null ||
                nickName.length() == 0 || password.length() == 0 || firstName.length() == 0 || secondName.length() == 0 || phoneNum.length() == 0) {
            hasError = true;
            errorString = "Not all line valid!" + nickName + password + firstName + secondName + phoneNum;
        } else {//данные все заполнены.
            try {
                // Найти user в DB.
                user = DBUtils.findUser(conn, nickName);

                if (user != null) {//пользователь найден запись не возможна
                    hasError = true;
                    errorString = "User nick name or password invalid";
                    consolLogger.info(errorString);
                }
            } catch (SQLException e) {
                hasError = false;
            }
        }
        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {// если такой же пользователь есть перенаправить обратно
            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
            errorString = errorString + birthDate + " " + date.toString() + " ";

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = context.getRequestDispatcher("/WEB-INF/views/registrationUser.jsp");

            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        //
        // И перенаправить к странице userInfo.
        else {
            user = new UserAccount();
            user.setNickName(nickName);
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setPhoneNumber(phoneNum);
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date dat;
            try {
                dat = formater.parse(birthDate[0]);
            } catch (ParseException e) {
                e.printStackTrace();
                dat = new Date();
                consolLogger.info("Date don't parse in format! " + birthDate[0]);
            }

            user.setBirthDate(dat);
            user.setPassword(password);
            user.setAccessRights("Client");
            user.setCod("0");
            try {
                DBUtils.addNewUser(conn, user);
                fileLogger.info(
                        " !!Add new user in base: first name " + user.getFirstName() + "," +
                        " second name " + user.getSecondName() + "," +
                        " nick name " + user.getNickName()+ ","+
                        " access rights "+ user.getAccessRights() + "," +
                        " phone number " + user.getPhoneNumber() + "," +
                        " birth date "+ user.getBirthDate().toString() + "");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            MyUtils.storeLoginedUser(request.getSession(), user);

            // Если пользователь выбирает функцию "Remember me".
            MyUtils.deleteUserCookie(response);

            // Redirect (Перенаправить) на страницу /userInfo.
            //response.sendRedirect(request.getContextPath() + "/userInfo");
        }
        InvokerServlet.commandsList.get("userInfo").execute(request,response,context);

    }
}
