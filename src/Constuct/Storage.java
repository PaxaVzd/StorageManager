package Constuct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Клас, що представляє сховище (склад) для управління товарами та операціями на складі.
 */
public class Storage {
    /**
     * Максимальна місткість складу.
     */
    private static final int MAX_CAPACITY = 100;

    /**
     * Поточна місткість складу.
     */
    private int currentCapacity;

    /**
     * Карта, яка відображає продукти на складі та їх кількість.
     */
    private Map<Product, Integer> productQuantities;

    /**
     * Список операцій, які відбулися на складі.
     */
    private List<Operation> operationHistory;

    /**
     * Конструктор для створення об'єкта складу з початковими значеннями.
     */
    public Storage() {
        this.currentCapacity = 0;
        this.productQuantities = new HashMap<>();
        this.operationHistory = new ArrayList<>();
    }

    /**
     * Додає новий продукт на склад та викликає відповідні операції.
     *
     * @param product Продукт для додавання на склад.
     * @throws StockException Виникає, якщо немає достатньої місця для нового продукту.
     */
    public void addProduct(Product product) throws StockException {
        int productQuantity = product.getQuantity();
        if (currentCapacity + productQuantity > MAX_CAPACITY) {
            throw new StockException("Недостатньо місця на складі.");
        }

        productQuantities.put(product, productQuantity);
        currentCapacity += productQuantity;
        String operationDescription = productQuantity + " кількість товару " + product.getName() +
                " додано на склад. Поточна місткість: " + currentCapacity;
        System.out.println(operationDescription);
        addToHistory(new Operation(operationDescription));
    }

    /**
     * Видаляє певну кількість продукту зі складу та викликає відповідні операції.
     *
     * @param productName     Назва продукту для видалення.
     * @param quantity        Кількість одиниць продукту для видалення.
     * @throws StockException Виникає, якщо вказаної кількості продукту немає на складі.
     */
    public void removeProduct(String productName, int quantity) throws StockException {
        for (Product product : productQuantities.keySet()) {
            if (product.getName().equals(productName)) {
                int productQuantity = productQuantities.get(product);
                if (productQuantity >= quantity) {
                    productQuantities.put(product, productQuantity - quantity);
                    currentCapacity -= quantity;
                    String operationDescription = quantity + " кількість товару " + productName +
                            " видалено зі складу. Поточна місткість: " + currentCapacity;
                    System.out.println(operationDescription);
                    addToHistory(new Operation(operationDescription));
                    return;
                } else {
                    throw new StockException("На складі недостатньо товару " + productName +
                            " для видалення вказаної кількості.");
                }
            }
        }

        throw new StockException("Товар " + productName + " не знайдено на складі.");
    }

    /**
     * Отримує карту, що містить продукти на складі та їх кількість.
     *
     * @return Карта продуктів та їх кількість на складі.
     */
    public Map<Product, Integer> getProductQuantities() {
        return productQuantities;
    }

    /**
     * Отримує список операцій, які відбулися на складі.
     *
     * @return Список операцій на складі.
     */
    public List<Operation> getOperationHistory() {
        return operationHistory;
    }

    /**
     * Додає нову операцію до історії операцій на складі.
     *
     * @param operation Операція для додавання до історії.
     */
    private void addToHistory(Operation operation) {
        operationHistory.add(operation);
    }
}
