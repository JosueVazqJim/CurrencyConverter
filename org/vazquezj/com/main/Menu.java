package org.vazquezj.com.main;

import org.vazquezj.com.connection.APIConnection;
import org.vazquezj.com.modelos.CambioResponse;

import java.util.Scanner;

public class Menu {
    // Método para mostrar el menú y gestionar las opciones
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        APIConnection apiConnection = new APIConnection();  // Crear una instancia de APIConnection

        while (true) {
            // Mostrar las opciones
            System.out.println("===== Exchange Rate  =====");
            System.out.println("1. Make Conversion");
            System.out.println("2. Exit");
            System.out.print("Choose an option (1 o 2): ");

            int option = scanner.nextInt();

            // Opción 1: Realizar conversión de moneda
            if (option == 1) {
                System.out.print("Enter base currency (ej. USD): ");
                String base = scanner.next().toUpperCase();  // Convertir a mayúsculas para que sea consistente
                System.out.print("Enter target currency (ej. EUR): ");
                String target = scanner.next().toUpperCase();  // Convertir a mayúsculas
                System.out.print("Enter the amount: ");
                double amount = scanner.nextDouble();

                // Llamar al método para realizar la conversión
                CambioResponse result = apiConnection.convertCurrency(base, target, amount);

                if (result != null) {
                    // Si la conversión fue exitosa, mostrar los resultados
                    System.out.println("\nSuccess:");
                    System.out.println("base Currency: " + base);
                    System.out.println("Target Currency: " + target);
                    System.out.println("Amount: " + amount);
                    System.out.println(result);
                } else {
                    System.out.println("Hubo un error al realizar la conversión. Intenta de nuevo.");
                }
            }
            // Opción 2: Salir
            else if (option == 2) {
                System.out.println("Thnaks for using the app. Goodbye!");
                break;
            }
            // Si el usuario elige una opción no válida
            else {
                System.out.println("No valid option. Please try again.");
            }
        }

        scanner.close();  // Cerrar el scanner al terminar
    }
}
