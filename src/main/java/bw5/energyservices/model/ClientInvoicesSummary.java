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
public class ClientInvoicesSummary {

    private Map<String, Map<String, Object>> invoicesSummary;
    private Map<Integer, Double> totalRevenueByYear;

    public ClientInvoicesSummary(List<Invoice> invoices) {
        this.invoicesSummary = generateInvoiceSummary(invoices);
        this.totalRevenueByYear = getTotalRevenueByYear(invoices);
    }

    //
    // Metodo per generare la mappa con lo stato delle fatture e l'importo totale
    // per ciascuno stato
    //
    private Map<String, Map<String, Object>> generateInvoiceSummary(List<Invoice> invoices) {
        return invoices.stream()
                .collect(Collectors.groupingBy(invoice -> invoice.getInvoiceStatus().getStatus(),
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            double totalAmount = list.stream().mapToDouble(Invoice::getAmount).sum();
                            int count = list.size();
                            Map<String, Object> summary = Map.of("totalAmount", totalAmount, "count", count);
                            return summary;
                        })));
    }

    public Map<Integer, Double> getTotalRevenueByYear(List<Invoice> invoices) {
        return invoices.stream()
                .collect(Collectors.groupingBy(invoice -> invoice.getInvoiceDate().getYear(),
                        Collectors.summingDouble(Invoice::getAmount)));

    }
}
