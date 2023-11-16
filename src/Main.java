import Constuct.Operation;
import Constuct.Product;
import Constuct.StockException;
import Constuct.Storage;

import java.util.Map;
import java.util.Scanner;

/**
 * Головний клас для виконання інтерактивного меню управління складом.
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Storage storage = new Storage();

    /**
     * Головний метод програми, який запускає головний цикл меню.
     *
     * @param args Параметри командного рядка (не використовуються в даному випадку).
     */
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

        scanner.close();
    }

    /**
     * Виводить на екран меню для користувача.
     */
    private static void printMenu() {
        System.out.println("\nОберіть дію:");
        System.out.println("1. Додати продукт");
        System.out.println("2. Видалити товар");
        System.out.println("3. Вивести інформацію про всі товари");
        System.out.println("4. Вивести історію операцій");
        System.out.println("5. Вийти");
    }

    /**
     * Додає новий продукт на склад.
     *
     * @throws StockException Виникає, якщо виникає помилка на складі.
     */
    private static void addProduct() throws StockException {
        System.out.println("Додавання товару на склад.");
        System.out.print("Введіть ім'я товару: ");
        String productName = scanner.nextLine();

        int productQuantity;
        do {
            System.out.print("Введіть кількість товару: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Некоректне введення. Будь ласка, введіть ціле число.");
                scanner.next();
            }
            productQuantity = scanner.nextInt();
        } while (productQuantity <= 0);

        scanner.nextLine();

        System.out.print("Введіть опис товару: ");
        String productDescription = scanner.nextLine();

        storage.addProduct(new Product(productName, productQuantity, productDescription));
    }

    /**
     * Видаляє товар зі складу.
     *
     * @throws StockException Виникає, якщо виникає помилка на складі.
     */
    private static void removeProduct() throws StockException {
        System.out.println("Видалення товару зі складу.");
        System.out.print("Введіть ім'я товару для видалення: ");
        String removeProductName = scanner.nextLine();

        int removeProductQuantity;
        do {
            System.out.print("Введіть кількість товару для видалення: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Некоректне введення. Будь ласка, введіть ціле число.");
                scanner.next(); // видаляємо некоректний ввід
            }
            removeProductQuantity = scanner.nextInt();
        } while (removeProductQuantity <= 0);

        scanner.nextLine();

        storage.removeProduct(removeProductName, removeProductQuantity);
    }

    /**
     * Виводить інформацію про всі товари на складі.
     */
    private static void displayProductInformation() {
        System.out.println("Інформація про всі товари на складі:");
        for (Map.Entry<Product, Integer> entry : storage.getProductQuantities().entrySet()) {
            Product product = entry.getKey();
            int productQuantity = entry.getValue();
            System.out.println(product.getName() + ": Кількість - " + productQuantity +
                    ", Опис - " + product.getDescription());
        }
    }

    /**
     * Виводить історію операцій на складі.
     */
    private static void displayOperationHistory() {
        System.out.println("Історія операцій на складі:");
        for (Operation operation : storage.getOperationHistory()) {
            System.out.println(operation.getDescription());
        }
    }
}
