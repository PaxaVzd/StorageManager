package Constuct;

/**
 * Клас, що представляє товар на складі.
 */
public class Product {
    /**
     * Назва товару.
     */
    private String name;

    /**
     * Кількість одиниць товару.
     */
    private int quantity;

    /**
     * Опис товару.
     */
    private String description;

    /**
     * Конструктор для створення об'єкта товару з вказаною назвою, кількістю та описом.
     *
     * @param name        Назва товару.
     * @param quantity    Кількість одиниць товару.
     * @param description Опис товару.
     */
    public Product(String name, int quantity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    /**
     * Отримує назву товару.
     *
     * @return Назва товару.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює нову назву товару.
     *
     * @param name Нова назва товару.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Отримує кількість одиниць товару.
     *
     * @return Кількість одиниць товару.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Встановлює нову кількість одиниць товару.
     *
     * @param quantity Нова кількість одиниць товару.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Отримує опис товару.
     *
     * @return Опис товару.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Встановлює новий опис товару.
     *
     * @param description Новий опис товару.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
