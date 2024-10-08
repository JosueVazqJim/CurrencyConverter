package org.vazquezj.com.main;

import org.vazquezj.com.connection.APIConnection;
import org.vazquezj.com.connection.CurrencyValidator;
import org.vazquezj.com.math.ConversionsOperations;
import org.vazquezj.com.modelos.CurrencyResponse;
import org.vazquezj.com.modelos.ExchangeRateResponse;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private CurrencyValidator currencyValidator;
    private ConversionsOperations conversionsOperations;

    public Menu() {
        this.currencyValidator = new CurrencyValidator("org/vazquezj/com/sources/currency.txt");
        this.conversionsOperations = new ConversionsOperations();
    }

    // Método para mostrar el menú y gestionar las opciones
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        APIConnection apiConnection = new APIConnection();  // Crear una instancia de APIConnection

        while (true) {
            // Mostrar las opciones
            System.out.println("===== Exchange Rate  =====");
            System.out.println("1. Make Conversion");
            System.out.println("2. Show Valid Currencies");
            System.out.println("3. USD -> MXN");
            System.out.println("4. MXN -> USD");
            System.out.println("5. EUR -> MXN");
            System.out.println("6. MXN -> EUR");
            System.out.println("7. ARS -> MXN");
            System.out.println("8. MXN -> ARS");
            System.out.println("9. Show History");
            System.out.println("0. Exit");
            System.out.print("Choose an option (0 to 9): ");

            int option = scanner.nextInt();

            // Opción 1: Realizar conversión de moneda
            if (option == 1) {
                String base = getValidCurrency(scanner, "base");
                String target = getValidCurrency(scanner, "target");
                double amount = getValidAmount(scanner);

                // Llamar al método para realizar la conversión
                CurrencyResponse result = apiConnection.convertCurrency(base, target, amount);

                if (result != null) {
                    // Si la conversión fue exitosa, mostrar los resultados
                    System.out.println("\nSuccess:");
                    System.out.println("base Currency: " + base);
                    System.out.println("Target Currency: " + target);
                    System.out.println("Amount: " + amount);
                    System.out.println(result);
                } else {
                    System.out.println("Oops, something not works. Try again.");
                }
            }

            else if (option == 2) {
                showValidCurrencies();
            } else if (option == 3) {
                double amount = getValidAmount(scanner);
                ExchangeRateResponse result = apiConnection.requestCurrency("USD");

                if (result != null) {
                    // Si la conversión fue exitosa, mostrar los resultados
                    double conversionCurrency = conversionsOperations.convertCurrency(result, amount, "MXN");
                    System.out.println("\nSuccess:");
                    System.out.println("base Currency: USD");
                    System.out.println("Target Currency: MXN");
                    System.out.println("Amount: " + amount);
                    System.out.println("Conversion result: " + conversionCurrency);
                } else {
                    System.out.println("Oops, something not works. Try again.");
                }
            } else if (option == 4){
                double amount = getValidAmount(scanner);
                ExchangeRateResponse result = apiConnection.requestCurrency("MXN");

                if (result != null) {
                    // Si la conversión fue exitosa, mostrar los resultados
                    double conversionCurrency = conversionsOperations.convertCurrency(result, amount, "USD");
                    System.out.println("\nSuccess:");
                    System.out.println("base Currency: MXN");
                    System.out.println("Target Currency: USD");
                    System.out.println("Amount: " + amount);
                    System.out.println("Conversion result: " + conversionCurrency);
                } else {
                    System.out.println("Oops, something not works. Try again.");
                }
            }else if(option == 5){
                double amount = getValidAmount(scanner);
                ExchangeRateResponse result = apiConnection.requestCurrency("EUR");

                if (result != null) {
                    // Si la conversión fue exitosa, mostrar los resultados
                    double conversionCurrency = conversionsOperations.convertCurrency(result, amount, "MXN");
                    System.out.println("\nSuccess:");
                    System.out.println("base Currency: EUR");
                    System.out.println("Target Currency: MXN");
                    System.out.println("Amount: " + amount);
                    System.out.println("Conversion result: " + conversionCurrency);
                } else {
                    System.out.println("Oops, something not works. Try again.");
                }
            } else if (option == 6){
                double amount = getValidAmount(scanner);
                ExchangeRateResponse result = apiConnection.requestCurrency("MXN");

                if (result != null) {
                    // Si la conversión fue exitosa, mostrar los resultados
                    double conversionCurrency = conversionsOperations.convertCurrency(result, amount, "EUR");
                    System.out.println("\nSuccess:");
                    System.out.println("base Currency: MXN");
                    System.out.println("Target Currency: EUR");
                    System.out.println("Amount: " + amount);
                    System.out.println("Conversion result: " + conversionCurrency);
                } else {
                    System.out.println("Oops, something not works. Try again.");
                }
            }else if (option == 7){
                double amount = getValidAmount(scanner);
                ExchangeRateResponse result = apiConnection.requestCurrency("ARS");

                if (result != null) {
                    // Si la conversión fue exitosa, mostrar los resultados
                    double conversionCurrency = conversionsOperations.convertCurrency(result, amount, "MXN");
                    System.out.println("\nSuccess:");
                    System.out.println("base Currency: ARS");
                    System.out.println("Target Currency: MXN");
                    System.out.println("Amount: " + amount);
                    System.out.println("Conversion result: " + conversionCurrency);
                } else {
                    System.out.println("Oops, something not works. Try again.");
                }
            }else if (option == 7){
                double amount = getValidAmount(scanner);
                ExchangeRateResponse result = apiConnection.requestCurrency("MXN");

                if (result != null) {
                    // Si la conversión fue exitosa, mostrar los resultados
                    double conversionCurrency = conversionsOperations.convertCurrency(result, amount, "ARS");
                    System.out.println("\nSuccess:");
                    System.out.println("base Currency: MXN");
                    System.out.println("Target Currency: ARS");
                    System.out.println("Amount: " + amount);
                    System.out.println("Conversion result: " + conversionCurrency);
                } else {
                    System.out.println("Oops, something not works. Try again.");
                }
            }else if (option == 9) {
                apiConnection.showHistory();
            }
            // Opción 3: Salir
            else if (option == 0) {
                apiConnection.saveRequest();  // Guardar los datos en un archivo
                System.out.println("Thanks for using the Currency Converter. Goodbye!");
                break;
            }
            //por si introducen caracteres
            else if (option != Integer.parseInt(String.valueOf(option))) {
                System.out.println("Invalid option. Please try again.");
            }
            // Si el usuario elige una opción no válida
            else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();  // Cerrar el scanner al terminar
    }

    // Método para mostrar las monedas válidas
    private void showValidCurrencies() {
        List<String> validCurrencies = currencyValidator.getValidCurrencies();

        System.out.println("\n===== Valid Currencies =====");
        for (String currency : validCurrencies) {
            System.out.println(currency);
        }
        System.out.println("==========================\n");
    }

    private String getValidCurrency(Scanner scanner, String type) {
        String currency = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter " + type + " currency (ej. USD): ");
            currency = scanner.next().toUpperCase();  // Convertir a mayúsculas para consistencia

            // Verificar si el valor ingresado es un código de moneda válido
            if (currency.matches("[A-Za-z]{3}")) {  // Un código de moneda debe ser de 3 letras (Ej: USD, EUR)
                if (currencyValidator.isValidCurrency(currency)) {
                    valid = true;
                } else {
                    System.out.println("This currency is not supported. Please try again.");
                }
            } else {
                System.out.println("Oops, something not works. Try again with only 3 letters");
            }
        }
        return currency;
    }

    // Método para obtener una cantidad válida (double)
    private double getValidAmount(Scanner scanner) {
        double amount = 0.0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Introduce the amount: ");
                amount = scanner.nextDouble();

                // Verificar si la cantidad es mayor a 0
                if (amount > 0) {
                    valid = true;
                } else {
                    System.out.println("It must be higher than 0.");
                }
            } catch (Exception e) {
                System.out.println("Please, it must be a valid number.");
                scanner.next();  // Limpiar el buffer del scanner
            }
        }
        return amount;
    }
}
