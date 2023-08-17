package com.axxes.testing.mocking.unittesting.demo;

public interface InvoiceRepository {

    boolean hasOutStandingInvoices(int customerId);

    int getOutstandingInvoicesCount(int customerId);

}
