package com.Invoicer.Biller.Controllers;

import com.Invoicer.Biller.Repos.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @GetMapping(value = "/newinvoicenumber")
    public String getInvoiceCount(){
         String invoiceNo;
//        System.out.println(invoiceRepository.count()+1);
        invoiceNo="INV"+String.format("%04d",(invoiceRepository.count()+1));

    return invoiceNo;
    }
}
