package org.vazquezj.com.connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyValidator {
    private List<String> validCurrencies;

    public CurrencyValidator(String fileName) {
        validCurrencies = new ArrayList<>();
        readValidCurrencies(fileName);
    }

    private void readValidCurrencies(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                validCurrencies.add(line.trim().toUpperCase());  // Guardar en mayúsculas para consistencia
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de monedas: " + e.getMessage());
        }
    }

    // Método para validar si una moneda es válida
    public boolean isValidCurrency(String currency) {
        return validCurrencies.contains(currency.toUpperCase());
    }

    // Método para devolver todas las monedas válidas
    public List<String> getValidCurrencies() {
        return validCurrencies;
    }
}
