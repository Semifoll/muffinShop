package comServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Мастер класс сервлет. Осуществляет вызов оброботчиков по URL
 * @version 1.0
 * @autor Trusov Anton
 */
public class InvokerServlet extends HttpServlet {
    /**
     * Свойство - множество где: индекс(URL), значение(ссылка на обработчик).
     */

    private static final long serialVersionUID = 1L;
    public static Map<String, Command> commandsList = new HashMap<String,Command>();;

    /**
     * Конструктор класса.
     */
    public InvokerServlet(){
        super();
        //здесь заполнить список псевдо сервлетами(командами выполнения)
        //1-
        commandsList.put("Home", new GetPageHome());
        commandsList.put("infoPage", new GetPageInfo());
        commandsList.put("def", new GetPageHome());
        commandsList.put("ourProductPage", new GetPageProducts());
        commandsList.put("loginPage", new GetPageLogin());
        commandsList.put("registrationPage", new GetPageRegistration());
        commandsList.put("clientOrdersPage", new GetPageOrders());
        commandsList.put("wOrders", new GetPageOrdersWorker());
        commandsList.put("userInfo", new GetPageUserInfo());

        commandsList.put("addNewUser", new AddNewUser());
        commandsList.put("authorization", new Authorization());
        commandsList.put("changeStatus", new ChangeOrderStatus());
        commandsList.put("changeProduct", new ChangeProduct());
        commandsList.put("changeUserRole", new ChangeUserRole());
        commandsList.put("createProduct", new CreateProduct());
        commandsList.put("deleteProduct", new DeleteProduct());

        commandsList.put("pageAccountView", new GetPageAccountView());
        commandsList.put("pageAdminProductList", new GetPageAdminProductList());
        commandsList.put("buyProduct", new BuyProduct());
        commandsList.put("PageEditProduct", new GetPageEditProduct());
        commandsList.put("createProductPage", new GetPageCreateProduct());
        commandsList.put("errorPage", new ErrorPage());
        commandsList.put("loginOut", new LogOut());

        //-1

    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Servlet Url-pattern: /spath/*
        //
        // => /spath
        final Logger consolLogger = LogManager.getLogger();
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String newPage;
        String var_temp;
        try{
            newPage = request.getParameter("newPage").toString();//var_temp1
        }catch (NullPointerException e){
            newPage = "def";
        }

        String urlPattern = servletPath;
        Command page;
        // Коллекционировать все Servlet в вашем WebApp.
        if(commandsList.get(newPage)!= null ) {
            page = commandsList.get(newPage);
            try {
                page.execute(request, response, this.getServletContext());
            } catch (ServletException | IOException e) {
                consolLogger.error("Problem with invoker.");
                page = commandsList.get("errorPage");
                String errorString = e.getMessage();
                request.setAttribute("errorString", errorString);
                page.execute(request, response, this.getServletContext());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
