package org.vazquezj.com.modelos;

public record CambioRecord(String result, String time_last_update_utc,
                           String time_next_update_utc, double conversion_rate, double conversion_result) {
}
