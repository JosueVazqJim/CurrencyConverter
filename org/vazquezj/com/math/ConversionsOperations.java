package org.vazquezj.com.math;

import org.vazquezj.com.modelos.ExchangeRateResponse;

import java.util.Map;

public class ConversionsOperations {
    public double convertCurrency(ExchangeRateResponse exchangeRateResponse, double amount, String targetCurrency) {
        // Obtener el mapa de tasas de conversión
        Map<String, Double> conversionRates = exchangeRateResponse.getConversionRates();

        // Verificar si la moneda objetivo está en el mapa
        if (conversionRates.containsKey(targetCurrency)) {
            double conversionRate = conversionRates.get(targetCurrency);

            // Realizar la conversión
            return amount * conversionRate;
        } else {
            // Si no encontramos la moneda objetivo en las tasas de cambio, retornar -1 (error)
            System.out.println("Error, the target currency is not in the conversion rates.");
            return -1;
        }
    }
}
