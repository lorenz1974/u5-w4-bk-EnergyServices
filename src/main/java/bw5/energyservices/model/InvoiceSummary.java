package bw5.energyservices.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceSummary {

    private Map<String, Double> invoiceSummary;

    public InvoiceSummary(List<Invoice> invoices) {
        this.invoiceSummary = generateInvoiceSummary(invoices);
    }

    //
    // Metodo per generare la mappa con lo stato delle fatture e l'importo totale
    // per ciascuno stato
    //
    private Map<String, Double> generateInvoiceSummary(List<Invoice> invoices) {
        return invoices.stream()
                .collect(Collectors.groupingBy(invoice -> invoice.getInvoiceStatus().getStatus(),
                        Collectors.summingDouble(Invoice::getAmount)));
    }

    public Map<String, Double> getInvoiceSummary() {
        return invoiceSummary;
    }
}
