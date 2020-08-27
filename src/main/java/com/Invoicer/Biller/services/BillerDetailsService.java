package com.Invoicer.Biller.services;

import com.Invoicer.Biller.Models.Biller;
import com.Invoicer.Biller.Models.BillerDetails;
import com.Invoicer.Biller.Repos.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BillerDetailsService implements UserDetailsService {
    @Autowired
    private BillerRepository billerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Biller biller=billerRepository.findByEmail(email);
        if(biller!=null){
        return new BillerDetails(biller);
        }
        else{
            throw new UsernameNotFoundException("Biller not found");
        }
    }
}
