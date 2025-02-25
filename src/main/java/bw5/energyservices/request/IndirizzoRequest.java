package bw5.energyservices.request;

import jakarta.validation.constraints.NotEmpty;

public record IndirizzoRequest(
        @NotEmpty(message = "Indirizzo non può essere vuoto")
        String via,
        @NotEmpty(message = "Civico non può essere vuoto")
        String civico,
        @NotEmpty(message = "Cap non può essere vuoto")
        String cap,
        @NotEmpty(message = "Località non può essere vuota")
        String localita
) {
        public int prov_code() {
                return Integer.parseInt(cap.substring(0, 2));
        }

        public long cityId() {
                return Long.parseLong(cap.substring(2));
        }
}
