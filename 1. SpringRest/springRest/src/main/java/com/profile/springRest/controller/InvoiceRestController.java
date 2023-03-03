package com.profile.springRest.controller;

import com.profile.springRest.entity.Invoice;
import com.profile.springRest.exception.InvoiceNotFoundException;
import com.profile.springRest.service.InvoiceService;
import com.profile.springRest.util.InvoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/")
public class InvoiceRestController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceUtil invoiceUtil;

    @PostMapping("/invoices")
    public ResponseEntity<String> saveInvoice(@RequestBody Invoice inv){
        ResponseEntity<String> resp = null;
        try{
            Long id = invoiceService.SaveInvoice(inv);
            resp = new ResponseEntity<String>("Invoice " + id + " created", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            resp=new ResponseEntity<String>("Unable to save Invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @GetMapping("invoices")
    public ResponseEntity<?> getAllInvoices(){
        ResponseEntity<?> resp=null;
        try {
            resp= new ResponseEntity<List<Invoice>>(invoiceService.getAllInvoice(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to get Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<?> getOneInvoice(@PathVariable Long id){
        ResponseEntity<?> resp= null;
        try {
            resp= new ResponseEntity<Invoice>(invoiceService.getOneInvoice(id),HttpStatus.OK);
        }catch (InvoiceNotFoundException nfe) {
            throw nfe;
        }catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to find Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id){

        ResponseEntity<String> resp= null;
        try {
            invoiceService.deleteInvoice(id);
            resp= new ResponseEntity<String> (
                    "Invoice '"+id+"' deleted",HttpStatus.OK);

        } catch (InvoiceNotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            e.printStackTrace();
            resp= new ResponseEntity<String>(
                    "Unable to delete Invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    @PutMapping("/invoices/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice){

        ResponseEntity<String> resp = null;
        try {
            //db Object
            Invoice inv= invoiceService.getOneInvoice(id);
            //copy non-null values from request to Database object
            invoiceUtil.copyNonNullValues(invoice, inv);
            //finally update this object
            invoiceService.updateInvoice(inv);
            resp = new ResponseEntity<String>(
                    //"Invoice '"+id+"' Updated",
                    HttpStatus.RESET_CONTENT); //205- Reset-Content(PUT)

        } catch (InvoiceNotFoundException nfe) {
            throw nfe; // re-throw exception to handler
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to Update Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500-ISE
        }
        return resp;
    }

    /**
     * To update one Invoice just like where clause condition, updates Invoice object & returns Status as ResponseEntity<String>
     */
    @PatchMapping("/invoices/{id}/{number}")
    public ResponseEntity<String> updateInvoiceNumberById(
            @PathVariable Long id,
            @PathVariable String number
    )
    {
        ResponseEntity<String> resp = null;
        try {
            invoiceService.updateInvoiceNumberById(number, id);
            resp = new ResponseEntity<String>(
                    "Invoice '"+number+"' Updated",
                    HttpStatus.PARTIAL_CONTENT); //206- Reset-Content(PUT)

        } catch(InvoiceNotFoundException pne) {
            throw pne; // re-throw exception to handler
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to Update Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500-ISE
        }
        return resp;
    }

}
