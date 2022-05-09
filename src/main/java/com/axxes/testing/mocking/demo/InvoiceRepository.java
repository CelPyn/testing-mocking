package com.axxes.testing.mocking.demo;

public interface InvoiceRepository {

    boolean hasOutStandingInvoices(int customerId);

    int getOutstandingInvoicesCount(int customerId);

}
