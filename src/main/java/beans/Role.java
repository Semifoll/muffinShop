package beans;
/**
 * Класс служит для создания объекта роли с параметрами:
 * <b>codRole</b> - код роли ,</br>
 * <b>name</b> - название роли, </br>
 * @version 1.0
 * @autor Trusov Anton
 */
public class Role {
    /**
     * Свойство - код роли
     */
    private int codRole;
    /**
     * Свойство - название продукта
     */
    private String name;
    /** Создает новый объект роли с заданными значениями
     * @param codRole - код роли
     * @param name - название роли
     *
     */
    public Role(int codRole, String name){
        this.codRole = codRole;
        this.name = name;
    }
    /** Функция для получения значения поля {@link Role#codRole}
     * @return Возвращает код роли.
     */
    public int getCodRole() {
        return codRole;
    }
    /** Функция для назначения значения поля {@link Role#codRole}
     * @param codRole - код роли.
     */
    public void setCodRole(int codRole) {
        this.codRole = codRole;
    }
    /** Функция для получения значения поля {@link Role#name}
     * @return Возвращает название роли.
     */
    public String getName() {
        return name;
    }
    /** Функция для назначения значения поля {@link Role#name}
     * @param name - название роли.
     */
    public void setName(String name) {
        this.name = name;
    }
}
