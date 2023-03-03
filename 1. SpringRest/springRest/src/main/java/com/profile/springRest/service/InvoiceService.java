package com.profile.springRest.service;


import com.profile.springRest.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    Long SaveInvoice(Invoice inv);
    Long updateInvoice(Invoice e);
    void deleteInvoice(Long id);
    Invoice getOneInvoice(Long id);
    List<Invoice> getAllInvoice();
    boolean isInvoiceExist(Long id);
    Integer updateInvoiceNumberById(String number, Long id);

}
