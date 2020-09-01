package com.Invoicer.Biller.Controllers;

import com.Invoicer.Biller.Models.Biller;
import com.Invoicer.Biller.Models.LoginBillerRequest;
import com.Invoicer.Biller.Models.LoginBillerResponse;
import com.Invoicer.Biller.Models.Name;
import com.Invoicer.Biller.Repos.BillerRepository;
import com.Invoicer.Biller.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
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
    @PostMapping(value = "/api/biller")
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
    @PostMapping(value = "/api/login")

    public LoginBillerResponse loginUser(@RequestBody LoginBillerRequest loginBillerRequest){
        String token="";
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginBillerRequest.getUsername(),loginBillerRequest.getPassword()));

            Biller biller=billerRepository.findByEmail( loginBillerRequest.getUsername());
            token=jwtUtils.generateToken(biller.getEmail());
        }
        catch(Exception e){
           e.printStackTrace();
           throw e;
        }
        LoginBillerResponse loginBillerResponse= new LoginBillerResponse();
        loginBillerResponse.setToken(token);
       return loginBillerResponse;
    }
}

