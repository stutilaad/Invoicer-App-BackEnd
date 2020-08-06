package com.Invoicer.Biller.Controllers;

import com.Invoicer.Biller.Models.Biller;
import com.Invoicer.Biller.Models.Name;
import com.Invoicer.Biller.Repos.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class BillerController {
    @Autowired
    private BillerRepository billerRepository;

    @GetMapping(value = "/biller")
    public List<Biller> GetBillers() {
        return billerRepository.findAll();
    }

    @PostMapping(value = "/biller")
    public Biller InsertBiller(@RequestBody Biller biller) {
        return billerRepository.save(biller);
    }

    @PutMapping(value = "/biller")
    public Biller UpdateBiller(@RequestBody Biller billerDetails, @PathVariable String id) {

        Biller updatedBiller = billerRepository.findById(id).get();

        updatedBiller.setName(billerDetails.getName());
        updatedBiller.setAddress(billerDetails.getAddress());
        updatedBiller.setEmail(billerDetails.getEmail());
        updatedBiller.setPhone(billerDetails.getPhone());

        return billerRepository.save(updatedBiller);
    }
    @DeleteMapping(value = "/biller/{id}")
    public void DeleteBiller(@PathVariable String id){
        Biller deletedBiller = billerRepository.findById(id).get();
        billerRepository.delete(deletedBiller);
    }
}

