package org.vazquezj.com.modelos;

public class CambioResponse {
    private String result;
    private String time_last_update_utc;
    private String time_next_update_utc;
    private double conversion_rate;
    private double conversion_result;

    public CambioResponse() {
    }

    public CambioResponse(String result, String time_last_update_utc, String time_next_update_utc, double conversion_rate, double conversion_result) {
        this.result = result;
        this.time_last_update_utc = time_last_update_utc;
        this.time_next_update_utc = time_next_update_utc;
        this.conversion_rate = conversion_rate;
        this.conversion_result = conversion_result;
    }

    public  CambioResponse(CambioRecord cambioRecord) {
        this.result = cambioRecord.result();
        this.time_last_update_utc = cambioRecord.time_last_update_utc();
        this.time_next_update_utc = cambioRecord.time_next_update_utc();
        this.conversion_rate = cambioRecord.conversion_rate();
        this.conversion_result = cambioRecord.conversion_result();
    }

    @Override
    public String toString() {
        return "Time last update: '" + time_last_update_utc + '\n' +
                "Time next update: " + time_next_update_utc + '\n' +
                "Conversion rate: " + conversion_rate + '\n' +
                "Conversion result: " + conversion_result;
    }
}
