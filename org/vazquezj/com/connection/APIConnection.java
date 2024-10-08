package org.vazquezj.com.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.vazquezj.com.modelos.CambioRecord;
import org.vazquezj.com.modelos.CambioResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConnection {
    private  static final String BASE_URL = "https://v6.exchangerate-api.com/v6/043dc0c335288a9efc5cdd23";
    private HttpClient client;
    Gson gson;

    public APIConnection() {
        this.client = HttpClient.newHttpClient(); // Crear un cliente HTTP
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public CambioResponse convertCurrency(String base, String target, double amount) {
        String direccion = BASE_URL + "pair/" + base + "/" + target + "/" + amount;

        HttpRequest request = HttpRequest.newBuilder() // Crear una petición, pero aun no se ha enviado
                .uri(URI.create(direccion))
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

        String json = response.body(); //obtenemos el cuerpo de la respuesta en formato crudo

        // Usamos JsonParser para convertir el string JSON en un JsonObject que es un mapa clave-valor hecho objeto de Java
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        CambioResponse cambioResponse = null;
        if (jsonObject.has("result") && jsonObject.get("result").getAsString().equals("success")) {
            cambioResponse = new CambioResponse(jsonObject.get("result").getAsString(),
                    jsonObject.get("time_last_update_utc").getAsString(),
                    jsonObject.get("time_next_update_utc").getAsString(),
                    jsonObject.get("conversion_rate").getAsDouble(),
                    jsonObject.get("conversion_result").getAsDouble());
            return cambioResponse;
        }
        return cambioResponse;
    }
}
