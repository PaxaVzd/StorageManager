import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private static final int MAX_CAPACITY = 100;
    private int currentCapacity;
    private Map<Product, Integer> productSpaces;
    private List<Operation> operationHistory;

    public Storage() {
        this.currentCapacity = 0;
        this.productSpaces = new HashMap<>();
        this.operationHistory = new ArrayList<>();
    }

    public void addProduct(Product product) throws StockException {
        int productSpace = product.getQuantity(); // Площа товару - кількість одиниць товару
        if (currentCapacity + productSpace > MAX_CAPACITY) {
            throw new StockException("Недостатньо місця на складі для додавання товару.");
        }

        productSpaces.put(product, productSpace);
        currentCapacity += productSpace;
        String operationDescription = productSpace + " площа товару " + product.getName() +
                " додано на склад. Поточна місткість: " + currentCapacity;
        System.out.println(operationDescription);
        addToHistory(operationDescription);
    }

    public void removeProduct(String productName, int quantity) throws StockException {
        for (Product product : productSpaces.keySet()) {
            if (product.getName().equals(productName)) {
                int productSpace = productSpaces.get(product);
                if (productSpace >= quantity) {
                    productSpaces.put(product, productSpace - quantity);
                    currentCapacity -= quantity;
                    String operationDescription = quantity + " площа товару " + productName +
                            " видалено зі складу. Поточна місткість: " + currentCapacity;
                    System.out.println(operationDescription);
                    addToHistory(operationDescription);
                    return;
                } else {
                    throw new StockException("На складі недостатньо площі товару " + productName +
                            " для видалення вказаної кількості.");
                }
            }
        }

        throw new StockException("Товар " + productName + " не знайдено на складі.");
    }

    public Map<Product, Integer> getProductSpaces() {
        return productSpaces;
    }

    public List<Operation> getOperationHistory() {
        return operationHistory;
    }

    private void addToHistory(String operationDescription) {
        operationHistory.add(new Operation(operationDescription));
    }
}
