package beans;

/**
 * Класс служит для создания объекта продукта с параметрами:
 * <b>code</b> - код продукта ,</br>
 * <b>name</b> - название продукта, </br>
 * <b>price</b> - цена продукта </br>
 * <b>mass</b> - масса продукта</br>
 *
 * @version 1.0
 * @autor Trusov Anton
 */
public class Product {
    /**
     * Свойство - код продукта
     */
    private String code; //len = 10		not null
    /**
     * Свойство - название продукта
     */
    private String name; //len = 30		not null
    /**
     * Свойство - цена продукта
     */
    private float price; //len = 10(,2)
    /**
     * Свойство - масса продукта
     */
    private float mass; // len = 10(,2)
    /** Создает новый объект продукт с заданными значениями
     * @param code - код продукта
     * @param name - название продукта.
     * @param price - цена продукта
     * @param mass - масса продукта
     *
     */
    public Product(String code, String name, float price, float mass) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.mass = mass;
    }

    /**
     * Создает новый обьект продукт с null значениями.
     */
    public Product(){

    }
    /** Функция для получения значения поля {@link Product#code}
     * @return Возвращает идентификатор продукта
     */
    public String getCode() {
        return code;
    }
    /** Функция для назначения значения поля {@link Product#code}
     * @param code - идентификатор продукта
     */
    public void setCode(String code) {
        this.code = code;
    }
    /** Функция для получения значения поля {@link Product#name}
     * @return Возвращает название продукта
     */
    public String getName() {
        return name;
    }
    /** Функция для назначения значения поля {@link Product#name}
     * @param name - название продукта
     */
    public void setName(String name) {
        this.name = name;
    }
    /** Функция для получения значения поля {@link Product#price}
     * @return Возвращает цену продукта
     */
    public float getPrice() {
        return price;
    }
    /** Функция для назначения значения поля {@link Product#price}
     * @param price - цена продукта
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /** Функция для получения значения поля {@link Product#mass}
     * @return Возвращает массу продукта
     */
    public float getMass() {
        return mass;
    }
    /** Функция для назначения значения поля {@link Product#mass}
     * @param mass - массу продукта
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

}
