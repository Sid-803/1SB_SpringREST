package com.profile.springRest.service;

import com.profile.springRest.entity.Invoice;
import com.profile.springRest.exception.InvoiceNotFoundException;
import com.profile.springRest.repo.InvoiceRepo;
import com.profile.springRest.util.InvoiceUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private InvoiceUtil invoiceUtil;


    @Override
    public Long SaveInvoice(Invoice inv) {
        invoiceUtil.CalculateFinalAmountIncludingGST(inv);
        return invoiceRepo.save(inv).getId();
    }

    @Override
    public Long updateInvoice(Invoice e) {
        invoiceUtil.CalculateFinalAmountIncludingGST(e);
        return invoiceRepo.save(e).getId();
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepo.delete(getOneInvoice(id));
    }


    @Override
    public Invoice getOneInvoice(Long id) {
        Invoice invoice = invoiceRepo.findById(id).orElseThrow(()->new InvoiceNotFoundException(
                new StringBuffer()
                        .append("Product ")
                        .append(id)
                        .append(" not exist")
                        .toString()));
        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepo.findAll();
    }

    @Override
    public boolean isInvoiceExist(Long id) {
        return invoiceRepo.existsById(id);
    }

    @Override
    @Transactional
    public Integer updateInvoiceNumberById(String number, Long id) {
        if(!invoiceRepo.existsById(id)){
            throw new InvoiceNotFoundException(new StringBuffer()
                    .append("Invoice ")
                    .append(id)
                    .append(" not exist")
                    .toString());
        }
        return invoiceRepo.updateInvoiceNumberById(number,id);
    }
}
