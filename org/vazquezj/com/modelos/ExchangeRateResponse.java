package org.vazquezj.com.modelos;

import java.util.Map;

public class ExchangeRateResponse implements CurrencyConversion {
    private String result;
    private String timeLastUpdateUtc;
    private String timeNextUpdateUtc;
    private String baseCode;
    private Map<String, Double> conversionRates;

    public ExchangeRateResponse() {
    }

    public ExchangeRateResponse(String result, String timeLastUpdateUtc, String timeNextUpdateUtc, String baseCode, Map<String, Double> conversionRates) {
        this.result = result;
        this.timeLastUpdateUtc = timeLastUpdateUtc;
        this.timeNextUpdateUtc = timeNextUpdateUtc;
        this.baseCode = baseCode;
        this.conversionRates = conversionRates;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "result='" + result + '\'' +
                ", timeLastUpdateUtc='" + timeLastUpdateUtc + '\'' +
                ", timeNextUpdateUtc='" + timeNextUpdateUtc + '\'' +
                ", baseCode='" + baseCode + '\'' +
                ", conversionRates=" + conversionRates +
                '}';
    }
}
