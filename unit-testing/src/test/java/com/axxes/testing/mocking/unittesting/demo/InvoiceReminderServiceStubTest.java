package com.axxes.testing.mocking.unittesting.demo;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InvoiceReminderServiceStubTest {

    private final InvoiceRepository invoiceRepository = new StubInvoiceRepository();
    private final InvoiceReminderService invoiceReminderService = new InvoiceReminderService(invoiceRepository);

    @Test
    void when_customer_hasOutstandingInvoices_messageIsReturned() {
        Customer customer = new Customer(1, "John");

        Optional<String> result = invoiceReminderService.remindCustomer(customer);

        assertThat(result).isNotNull().isNotEmpty().contains("Dear John, you have 10 invoices left to pay. Please pay them!");
    }

    private static class StubInvoiceRepository implements InvoiceRepository {
        @Override
        public boolean hasOutStandingInvoices(int customerId) {
            return true;
        }

        @Override
        public int getOutstandingInvoicesCount(int customerId) {
            return 10;
        }
    }

}
