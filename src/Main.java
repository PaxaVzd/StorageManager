import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Storage storage = new Storage();

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        addProduct();
                        break;
                    case "2":
                        removeProduct();
                        break;
                    case "3":
                        displayProductInformation();
                        break;
                    case "4":
                        displayOperationHistory();
                        break;
                    case "5":
                        isRunning = false;
                        System.out.println("Програма завершена.");
                        break;
                    default:
                        System.out.println("Невірний вибір. Спробуйте ще раз.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Помилка формату числа. Будь ласка, введіть коректне значення.");
            } catch (StockException e) {
                System.err.println("Помилка на складі: " + e.getMessage());
            }
        }

        scanner.close(); // Закриття Scanner
    }

    private static void printMenu() {
        System.out.println("\nОберіть дію:");
        System.out.println("1. Додати продукт");
        System.out.println("2. Видалити товар");
        System.out.println("3. Вивести інформацію про всі товари");
        System.out.println("4. Вивести історію операцій");
        System.out.println("5. Вийти");
    }

    private static void addProduct() throws StockException {
        System.out.println("Додавання товару на склад.");
        System.out.print("Введіть ім'я товару: ");
        String productName = scanner.nextLine();
        System.out.print("Введіть кількість товару: ");
        int productQuantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Введіть опис товару: ");
        String productDescription = scanner.nextLine();

        storage.addProduct(new Product(productName, productQuantity, productDescription));
    }

    private static void removeProduct() throws StockException {
        System.out.println("Видалення товару зі складу.");
        System.out.print("Введіть ім'я товару для видалення: ");
        String removeProductName = scanner.nextLine();
        System.out.print("Введіть кількість товару для видалення: ");
        int removeProductQuantity = Integer.parseInt(scanner.nextLine());

        storage.removeProduct(removeProductName, removeProductQuantity);
    }

    private static void displayProductInformation() {
        System.out.println("Інформація про всі товари на складі:");
        for (Map.Entry<Product, Integer> entry : storage.getProductSpaces().entrySet()) {
            Product product = entry.getKey();
            int productSpace = entry.getValue();
            System.out.println(product.getName() + ": Площа - " + productSpace +
                    ", Кількість - " + product.getQuantity() + ", Опис - " + product.getDescription());
        }
    }

    private static void displayOperationHistory() {
        System.out.println("Історія операцій на складі:");
        for (Operation operation : storage.getOperationHistory()) {
            System.out.println(operation.getDescription());
        }
    }
}
