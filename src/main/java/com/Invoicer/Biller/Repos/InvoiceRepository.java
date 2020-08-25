package com.Invoicer.Biller.Repos;

import com.Invoicer.Biller.Models.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,String > {
}
