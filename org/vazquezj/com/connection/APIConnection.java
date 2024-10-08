package org.vazquezj.com.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.vazquezj.com.modelos.CurrencyConversion;
import org.vazquezj.com.modelos.CurrencyResponse;
import org.vazquezj.com.modelos.ExchangeRateResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class APIConnection {
    private static final String API_KEY = "043dc0c335288a9efc5cdd23";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/";
    private HttpClient client;
    private static Gson gson;

    private List<CurrencyConversion> lstCurrencyResponse;


    public APIConnection() {
        this.client = HttpClient.newHttpClient(); // Crear un cliente HTTP
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.lstCurrencyResponse = new ArrayList<CurrencyConversion>();
    }

    public ExchangeRateResponse requestCurrency(String base) {
        if (!validateParams(base)) {
            return null;
        }

        String direccion = makeUrl(base);
        ExchangeRateResponse exchangeRateResponse = parserSimpleRequest(fetchData(direccion, this.client));

        if (exchangeRateResponse == null) {
            return null;
        }

        lstCurrencyResponse.add(exchangeRateResponse);

        return exchangeRateResponse; //retornamos el json
    }

    public CurrencyResponse convertCurrency(String base, String target, double amount) {
        if (!validateParams(base, target, amount)) {
            return null;
        }

        String direccion = makeUrl(base, target, amount);

        String json = fetchData(direccion, this.client);

        CurrencyResponse currencyResponse = parserJSON(json);

        if (currencyResponse == null) {
            return null;
        }
        lstCurrencyResponse.add(currencyResponse);
        return currencyResponse;
    }

    private static boolean validateParams(String base) {
        if (base == null) {
            return false;
        }
        return !base.isEmpty();
    }

    private static boolean validateParams(String base, String target, double amount) {
        if (base == null || target == null) {
            return false;
        }
        if (base.isEmpty() || target.isEmpty()) {
            return false;
        }
        return !(amount <= 0);
    }

    private static String makeUrl(String base) {
        return BASE_URL + "latest/" + base ;
    }

    private static String makeUrl(String base, String target, double amount) {
        return BASE_URL + "pair/" + base + "/" + target + "/" + amount;
    }

    private static String fetchData(String requestUrl, HttpClient client) {
        HttpRequest request = HttpRequest.newBuilder() // Crear una petición, pero aun no se ha enviado
                .uri(URI.create(requestUrl))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client // Enviar la petición y recibir la respuesta
                    //El <String> es el tipo de dato que se espera recibir
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    private static ExchangeRateResponse parserSimpleRequest(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        ExchangeRateResponse exchangeRateResponse = null;
        JsonObject conversionRatesJson = jsonObject.getAsJsonObject("conversion_rates");
        Map<String, Double> conversionRates = gson.fromJson(conversionRatesJson, Map.class);
        if (jsonObject.has("result") && jsonObject.get("result").getAsString().equals("success")) {
            exchangeRateResponse = new ExchangeRateResponse(jsonObject.get("result").getAsString(),
                    jsonObject.get("time_last_update_utc").getAsString(),
                    jsonObject.get("time_next_update_utc").getAsString(),
                    jsonObject.get("base_code").getAsString(),
                    conversionRates);
            return exchangeRateResponse;
        }
        return exchangeRateResponse;
    }

    private static CurrencyResponse parserJSON(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        CurrencyResponse currencyResponse = null;
        if (jsonObject.has("result") && jsonObject.get("result").getAsString().equals("success")) {
            currencyResponse = new CurrencyResponse(jsonObject.get("result").getAsString(),
                    jsonObject.get("time_last_update_utc").getAsString(),
                    jsonObject.get("time_next_update_utc").getAsString(),
                    jsonObject.get("conversion_rate").getAsDouble(),
                    jsonObject.get("conversion_result").getAsDouble());
            return currencyResponse;
        }
        return currencyResponse;
    }

    public void showHistory() {
        //mostramos la lista
        for (CurrencyConversion currencyResponse : lstCurrencyResponse) {
            System.out.println(currencyResponse);
        }
    }

    public void saveRequest() {
        //probamos escribir sobre un archivo
        try {
            FileWriter escritura = new FileWriter("org/vazquezj/com/sources/requestHistoric.json");
            escritura.write(gson.toJson(lstCurrencyResponse)); //deboemos pasar el objeto a string y es posible pues ya sobreescribimos el metodo toString
            //podriamos escribir mejor un json
            escritura.close(); //cerramos el archivo
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
