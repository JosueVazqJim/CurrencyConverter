package org.vazquezj.com.main;

import org.vazquezj.com.connection.APIConnection;
import org.vazquezj.com.modelos.CambioResponse;

public class Main {
    public static void main(String[] args) {

        APIConnection apiConnection = new APIConnection();
        CambioResponse cambioResponse = apiConnection.convertCurrency("USD", "EUR", 100);
        System.out.println(cambioResponse);
    }
}
