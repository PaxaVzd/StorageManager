package Constuct;

/**
 * Клас, що представляє операцію на складі.
 */
public class Operation {
    /**
     * Опис операції.
     */
    private String description;

    /**
     * Конструктор для створення об'єкта операції з вказаним описом.
     *
     * @param description Опис операції.
     */
    public Operation(String description) {
        this.description = description;
    }

    /**
     * Отримує опис операції.
     *
     * @return Опис операції.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Встановлює новий опис операції.
     *
     * @param description Новий опис операції.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
