package com.Invoicer.Biller.Controllers;

import com.Invoicer.Biller.Models.Invoice;
import com.Invoicer.Biller.Models.InvoiceNo;
import com.Invoicer.Biller.Repos.InvoiceRepository;
import com.Invoicer.Biller.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping(value = "/newinvoicenumber")
    public InvoiceNo getInvoiceCount(){
         String invoiceNoString;
//        System.out.println(invoiceRepository.count()+1);
        invoiceNoString="INV"+String.format("%04d",(invoiceRepository.count()+1));
        InvoiceNo invoiceNo=new InvoiceNo();
        invoiceNo.setInvoiceNo(invoiceNoString);
    return invoiceNo;
    }
 @PostMapping(value = "/saveinvoice")
    public Invoice saveInvoice(@RequestBody Invoice invoice){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                     .getPrincipal();
        String username = userDetails.getUsername();
        invoice.setCreatedBy(username);
     return invoiceRepository.save(invoice);
 }
 @GetMapping(value = "/api/test")
public String streamString() throws IOException {
        String htmlfile="/templates/Invoices/index.html";
        String stringFile=Utils.streamToString(htmlfile);

        System.out.println(stringFile);
     stringFile= stringFile.replace("$payerName$","KK Laad");
     System.out.println(stringFile);
    return stringFile ;
}
}
