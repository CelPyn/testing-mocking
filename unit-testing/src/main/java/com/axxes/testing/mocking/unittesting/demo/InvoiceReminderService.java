package com.axxes.testing.mocking.unittesting.demo;

import java.util.Optional;

public class InvoiceReminderService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceReminderService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Optional<String> remindCustomer(Customer customer) {
        boolean hasOutStandingInvoices = invoiceRepository.hasOutStandingInvoices(customer.getId());

        if (!hasOutStandingInvoices) {
            return Optional.empty();
        }

        int invoicesCount = invoiceRepository.getOutstandingInvoicesCount(customer.getId());

        String message = "Dear " + customer.getName() + ", you have " + invoicesCount + " invoices left to pay. Please pay them!";

        return Optional.of(message);
    }
}
