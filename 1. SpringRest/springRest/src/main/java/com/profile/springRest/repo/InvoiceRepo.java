package com.profile.springRest.repo;

import com.profile.springRest.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepo extends JpaRepository<Invoice,Long> {
    //update
    @Modifying
    @Query("UPDATE Invoice SET number=:number WHERE id=:id")
    Integer updateInvoiceNumberById(String number,Long id);
}
