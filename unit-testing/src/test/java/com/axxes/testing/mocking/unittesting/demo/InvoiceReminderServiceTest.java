package com.axxes.testing.mocking.unittesting.demo;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class InvoiceReminderServiceTest {

    private final InvoiceRepository invoiceRepository = mock(InvoiceRepository.class);
    private final InvoiceReminderService invoiceReminderService = new InvoiceReminderService(invoiceRepository);

    @Test
    void when_customer_hasOutstandingInvoices_messageIsReturned() {
        Customer customer = new Customer(1, "John");

        when(invoiceRepository.hasOutStandingInvoices(1)).thenReturn(true);
        when(invoiceRepository.getOutstandingInvoicesCount(1)).thenReturn(15);

        Optional<String> result = invoiceReminderService.remindCustomer(customer);

        assertThat(result).isNotNull().isNotEmpty().contains("Dear John, you have 15 invoices left to pay. Please pay them!");

        verify(invoiceRepository, times(1)).hasOutStandingInvoices(1);
        verify(invoiceRepository, times(1)).getOutstandingInvoicesCount(1);
        verifyNoMoreInteractions(invoiceRepository);
    }

    @Test
    void when_customer_hasNoOutstandingInvoices_noMessageIsReturned() {
        Customer customer = new Customer(1, "John");

        when(invoiceRepository.hasOutStandingInvoices(1)).thenReturn(false);

        Optional<String> result = invoiceReminderService.remindCustomer(customer);

        assertThat(result).isNotNull().isEmpty();
        verify(invoiceRepository, times(1)).hasOutStandingInvoices(1);
        verifyNoMoreInteractions(invoiceRepository);
    }

}
