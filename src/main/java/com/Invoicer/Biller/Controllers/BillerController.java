package com.Invoicer.Biller.Controllers;

import com.Invoicer.Biller.Models.Biller;
import com.Invoicer.Biller.Models.Name;
import com.Invoicer.Biller.Repos.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;


@CrossOrigin
@RestController
public class BillerController {
    @Autowired
    private BillerRepository billerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
//GET api
    @GetMapping(value = "/biller")
    public List<Biller> GetBillers() {
        return billerRepository.findAll();
    }
    // get one biller
    @GetMapping(value="/biller/{id}")
    public Biller getOneBiller(@PathVariable String id){
        return billerRepository.findById(id).get();
    }
//POST api
    @PostMapping(value = "/biller")
    public Biller InsertBiller(@RequestBody Biller biller) {

        String encodedPassword=bCryptPasswordEncoder.encode(biller.getPassword());
        biller.setPassword(encodedPassword);
        return billerRepository.save(biller);
    }
//PUT api
    @PutMapping(value = "/biller")
    public Biller UpdateBiller(@RequestBody Biller billerDetails) {

        Biller updatedBiller = billerRepository.findById(billerDetails.getId()).get();

        updatedBiller.setName(billerDetails.getName());
        updatedBiller.setAddress(billerDetails.getAddress());
        updatedBiller.setEmail(billerDetails.getEmail());
        updatedBiller.setPhone(billerDetails.getPhone());

        return billerRepository.save(updatedBiller);
    }
    //DELETE api
    @DeleteMapping(value = "/biller/{id}")
    public void DeleteBiller(@PathVariable String id){
        Biller deletedBiller = billerRepository.findById(id).get();
        billerRepository.delete(deletedBiller);
    }
    //login api
    @PostMapping(value = "/login")
    public boolean loginUser(@RequestBody Biller biller){
        Biller loginBiller = billerRepository.findByEmail(biller.getEmail());
        System.out.println(loginBiller);
       return bCryptPasswordEncoder.matches(biller.getPassword(),loginBiller.getPassword());
    }
}

