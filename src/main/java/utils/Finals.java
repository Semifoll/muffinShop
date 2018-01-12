package utils;

import comServlet.*;

import java.util.HashMap;
import java.util.Map;

public class Finals {

    public Map<String, Command> commandsList = new HashMap<String,Command>();;

    public Finals(){
        //здесь заполнить список псевдо сервлетами(командами выполнения)
        TestServlet test = new TestServlet();
        TestInfoServlet testInfo = new TestInfoServlet();
        //1-
        AddNewUser addNewUser              = new AddNewUser();
        Authorization authorization           = new Authorization();
        ChangeOrderStatus changeOrderStatus       = new ChangeOrderStatus();
        ChangeProduct           changeProduct           = new ChangeProduct();
        ChangeUserRole          changeUserRole          = new ChangeUserRole();
        CreateProduct           createProduct           = new CreateProduct();
        DeleteProduct           deleteProduct           = new DeleteProduct();
        GetPageAccountView      getPageAccountView      = new GetPageAccountView();
        GetPageAdminProductList getPageAdminProductList = new GetPageAdminProductList();
        GetPageBuyProduct       getPageBuyProduct       = new GetPageBuyProduct();
        GetPageEditProduct      getPageEditProduct      = new GetPageEditProduct();
        GetPageHome             getPageHome             = new GetPageHome();
        GetPageInfo             getPageInfo             = new GetPageInfo();
        GetPageLogin            getPageLogin            = new GetPageLogin();
        GetPageOrders           getPageOrders           = new GetPageOrders();
        GetPageOrdersWorker     getPageOrdersWorker     = new GetPageOrdersWorker();
        GetPageProducts         getPageProducts         = new GetPageProducts();
        GetPageRegistration     getPageRegistration     = new GetPageRegistration();
        GetPageUserInfo         getPageUserInfo         = new GetPageUserInfo();

        GetPageHome def = new GetPageHome();
        //-1
        commandsList.put("/addNewUser",addNewUser);
        commandsList.put("/authorization",authorization);//!!!!!!!!!!
        commandsList.put("/changeOrderStatus",changeOrderStatus);
        commandsList.put("/changeProduct",changeProduct);//!!!!!!!!!!
        commandsList.put("/changeUserRole",changeUserRole);
        commandsList.put("/createProduct",createProduct);
        commandsList.put("/deleteProduct",deleteProduct);
        commandsList.put("/accountView",getPageAccountView);
        commandsList.put("/aProductList",getPageAdminProductList);
        commandsList.put("/buyProduct",getPageBuyProduct);
        commandsList.put("/editProduct",getPageEditProduct);
        commandsList.put("/home",getPageHome);
        commandsList.put("/info",getPageInfo);
        commandsList.put("/login",getPageLogin);
        commandsList.put("/orderList",getPageOrders);
        commandsList.put("/orderListWorker",getPageOrdersWorker);
        commandsList.put("/productList",getPageProducts);
        commandsList.put("/registration",getPageRegistration);
        commandsList.put("/userInfo",getPageUserInfo);

        commandsList.put("/*", def);
    }
}
