package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderObject extends Product {
    private int codOrder;
    private String userNickName;
    private String userCod;
    private String statusOrder;
    private int hour;
    private int min;
    private Date dateTime;
    private String dateString;

    public OrderObject(int codOrder,String code, String name, float price, float mass,
                       String userNickName, String userCod, String statusOrder, int hour, int min, Date dateTime) {
        super(code, name, price, mass);
        this.codOrder = codOrder;
        this.hour = hour;
        this.min = min;
        this.userNickName = userNickName;
        this.userCod = userCod;
        this.statusOrder = statusOrder;
        this.dateTime = dateTime;
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
        this.dateString = formater.format(dateTime);
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserCod() {
        return userCod;
    }

    public void setUserCod(String userCod) {
        this.userCod = userCod;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Date getDateTime() {
        return dateTime;
    }
    public String getDateString() {
        return dateString;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
        this.dateString = formater.format(dateTime);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getCodOrder() {
        return codOrder;
    }

    public void setCodOrder(int codOrder) {
        this.codOrder = codOrder;
    }
}