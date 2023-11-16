// Storage.java
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final int MAX_CAPACITY = 100;
    private int currentCapacity;
    private List<Product> products;
    private List<Operation> operationHistory;

    public Storage() {
        this.currentCapacity = 0;
        this.products = new ArrayList<>();
        this.operationHistory = new ArrayList<>();
    }

    public void addProduct(Product product) throws StockException {
        if (currentCapacity + product.getQuantity() > MAX_CAPACITY) {
            throw new StockException("Недостатньо місця на складі для додавання товару.");
        }

        products.add(product);
        currentCapacity += product.getQuantity();
        String operationDescription = product.getQuantity() + " одиниць товару " + product.getName() +
                " додано на склад. Поточна місткість: " + currentCapacity;
        System.out.println(operationDescription);
        addToHistory(operationDescription);
    }

    public void removeProduct(String productName, int quantity) throws StockException {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                if (product.getQuantity() >= quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    currentCapacity -= quantity;
                    String operationDescription = quantity + " одиниць товару " + productName +
                            " видалено зі складу. Поточна місткість: " + currentCapacity;
                    System.out.println(operationDescription);
                    addToHistory(operationDescription);
                    return;
                } else {
                    throw new StockException("На складі недостатньо одиниць товару " + productName +
                            " для видалення вказаної кількості.");
                }
            }
        }

        throw new StockException("Товар " + productName + " не знайдено на складі.");
    }

    public Product findProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public List<Operation> getOperationHistory() {
        return operationHistory;
    }

    private void addToHistory(String operationDescription) {
        operationHistory.add(new Operation(operationDescription));
    }
}
