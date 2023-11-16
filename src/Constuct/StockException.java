package Constuct;

/**
 * Виключення, яке виникає при помилках на складі.
 */
public class StockException extends Exception {
    /**
     * Конструктор для створення об'єкта виключення з вказаним повідомленням про помилку.
     *
     * @param message Повідомлення про помилку.
     */
    public StockException(String message) {
        super(message);
    }
}
