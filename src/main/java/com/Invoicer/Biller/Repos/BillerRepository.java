package com.Invoicer.Biller.Repos;

import com.Invoicer.Biller.Models.Biller;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerRepository extends MongoRepository<Biller, String>{
}
