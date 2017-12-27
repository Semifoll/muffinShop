package beans;

import java.text.SimpleDateFormat;
import java.util.Date;
/** Класс служит для хранения объекта одного заказа
 *      с параметрами:
 * <b>codOrder</b> - код заказа ,</br>
 * <b>statusOrder</b> - статус заказа. </br>
 * <h3>Данные по пользователю:</h3></br>
 *      <b>userNickName</b> - уникальный никнейм пользователя сделавшего заказ,</br>
 *      <b>userCod</b> - уникальный код идентификации пользователя.</br>
 * <h3>Время заказа:</h3></br>
 *      <b>hour</b> - часы,</br>
 *       <b>min</b> - минуты,</br>
 *      <b>dateTime</b> - дата заказа (dd/mm/yy),</br>
 *      <b>dateString</b> - представление даты заказа в строковом виде.</br>.
 * @autor Trusov Anton
 * @version 1.0
 */
public class OrderObject extends Product {
    /** Свойство - код заказа*/
    private int codOrder;
    /** Свойство - уникальный никнейм пользователя сделавшего заказ */
    private String userNickName;
    /** Свойство - уникальный код идентификации пользователя */
    private String userCod;
    /** Свойство - статус заказа */
    private String statusOrder;
    /** Свойство - часы */
    private int hour;
    /** Свойство - минуты */
    private int min;
    /** Свойство - дата заказа  */
    private Date dateTime;
    /** Свойство - представление даты заказа в строковом виде (dd/mm/yy) */
    private String dateString;

    /** Создает новый объект заказа с заданными значениями
     * @param codOrder - код заказа ,</br>
     * @param statusOrder</b> - статус заказа. </br>
     * @param userNickName - уникальный никнейм пользователя сделавшего заказ,</br>
     * @param userCod - уникальный код идентификации пользователя.</br>
     * @param hour - часы
     * @param min - минуты
     * @param dateTime - дата заказа (dd/mm/yy)
     *
     */
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

    /** Функция для получения значения поля {@link OrderObject#userNickName}
     * @return Возвращает никнейм пользователя
     */
    public String getUserNickName() {
        return userNickName;
    }

    /** Функция для назначения значения поля {@link OrderObject#userNickName}
     * @param userNickName - никнейм пользователя.
     */
    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
    /** Функция для получения значения поля {@link OrderObject#userCod}
     * @return Возвращает идентификатор пользователя
     */
    public String getUserCod() {
        return userCod;
    }
    /** Функция для назначения значения поля {@link OrderObject#userCod}
     * @param userCod - идентификатор пользователя.
     */
    public void setUserCod(String userCod) {
        this.userCod = userCod;
    }
    /** Функция для получения значения поля {@link OrderObject#statusOrder}
     * @return Возвращает статус заказа
     */
    public String getStatusOrder() {
        return statusOrder;
    }
    /** Функция для назначения значения поля {@link OrderObject#statusOrder}
     * @param statusOrder - статус заказа.
     */
    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
    /** Функция для получения значения поля {@link OrderObject#dateTime}
     * @return Возвращает дату заказа в формате даты.
     */
    public Date getDateTime() {
        return dateTime;
    }
    /** Функция для получения значения поля {@link OrderObject#dateTime}
     * @return Возвращает дату заказа в формате строки.
     */
    public String getDateString() {
        return dateString;
    }
    /** Функция для назначения значения поля {@link OrderObject#dateTime}
     * @param dateTime - дата заказа.
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
        this.dateString = formater.format(dateTime);
    }
    /** Функция для получения значения поля {@link OrderObject#hour}
     * @return Возвращает часы заказа.
     */
    public int getHour() {
        return hour;
    }
    /** Функция для назначения значения поля {@link OrderObject#hour}
     * @param hour - часы заказа.
     */
    public void setHour(int hour) {
        this.hour = hour;
    }
    /** Функция для получения значения поля {@link OrderObject#min}
     * @return Возвращает минуты заказа.
     */
    public int getMin() {
        return min;
    }
    /** Функция для назначения значения поля {@link OrderObject#min}
     * @param min - минуты заказа.
     */
    public void setMin(int min) {
        this.min = min;
    }
    /** Функция для получения значения поля {@link OrderObject#codOrder}
     * @return Возвращает идентификатор заказа.
     */
    public int getCodOrder() {
        return codOrder;
    }
    /** Функция для назначения значения поля {@link OrderObject#codOrder}
     * @param codOrder - идентификатор заказа.
     */
    public void setCodOrder(int codOrder) {
        this.codOrder = codOrder;
    }
}