package bw5.energyservices.runner;

import bw5.energyservices.model.Client;
import bw5.energyservices.model.Invoice;
import bw5.energyservices.model.InvoiceStatus;
import bw5.energyservices.repository.ClientRepository;
import bw5.energyservices.repository.InvoiceRepository;
import bw5.energyservices.repository.InvoiceStatusRepository;
import jakarta.transaction.Transactional;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Order(4)
@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InvoiceRunner implements CommandLineRunner {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceStatusRepository invoiceStatusRepository;
    private final ClientRepository clientRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {

        log.info("Creating invoice statuses...");
        // Crea lo stato delle fatture
        // uso 'null' come id perché ci pensa Spring a generarlo
        invoiceStatusRepository.save(new InvoiceStatus(null, "Emessa", "Fattura Emessa"));
        invoiceStatusRepository.save(new InvoiceStatus(null, "Pagata", "Fattura Pagata"));
        invoiceStatusRepository.save(new InvoiceStatus(null, "Scaduta", "Fattura Scaduta"));
        invoiceStatusRepository.save(new InvoiceStatus(null, "Annullata", "Fattura Annullata"));

        log.info("Invoice statuses created.");

        log.info("Creating 150 invoices...");
        // Cerca il numero dei clienti nel DB
        long clientCount = clientRepository.count();
        log.debug("Clients count: {}", clientCount);

        // La data della prima fattura è n giorni fa
        LocalDate invoicePreviousDate = LocalDate.now().minusDays(365);
        log.debug("Previous date: {}", invoicePreviousDate);

        // Cerca i vari stati delle fatture
        long invoiceStatusCount = invoiceStatusRepository.count();
        log.debug("Invoice statuses count: {}", invoiceStatusCount);

        // Crea 50 fatture
        for (int i = 0; i < 150; i++) {

            //
            // La fattura viene creata con una data successiva alla precedente di massimo
            // due giorni
            //
            LocalDate invoiceDate;
            boolean sameDate = faker.bool().bool();
            log.debug("Same date: {}", sameDate);

            if (sameDate) {
                invoiceDate = invoicePreviousDate.plusDays(1);
                invoicePreviousDate = invoiceDate;
            } else {
                invoiceDate = invoicePreviousDate.plusDays(faker.number().numberBetween(1, 2));
                invoicePreviousDate = invoiceDate;
            }

            Invoice invoice = new Invoice();

            invoice.setInvoiceDate(invoiceDate);
            invoice.setInvoiceNumber(String.format("%06d", i));

            Client client = clientRepository.findById(faker.number().numberBetween(1, clientCount)).get();
            log.debug("Client: {}", client.toString());
            invoice.setClient(client);
            invoice.setAmount(BigDecimal.valueOf(faker.number().randomDouble(2, 9, 10000)));

            long randomStatusId = faker.number().numberBetween(1, invoiceStatusCount);
            InvoiceStatus randomStatus = invoiceStatusRepository.findById(randomStatusId).get();
            log.debug("Random status: {}", randomStatus);

            invoice.setInvoiceStatus(randomStatus);

            log.debug("Invoice: {}", invoice);

            invoiceRepository.save(invoice);

            // Scanner scanner = new Scanner(System.in);
            // System.out.println("Press Enter to continue...");
            // scanner.nextLine();

        }
        log.info("Invoices created.");

    }

}