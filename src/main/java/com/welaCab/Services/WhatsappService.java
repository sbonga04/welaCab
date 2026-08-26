package com.welaCab.Services;

import com.welaCab.Repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {
    @Autowired
    private TwilioService twilioService;

    public String handleMessege(String from,String message){
        System.out.println("welcome home");
        return "welcome to welacab";}
    //not crashing, but it's not giving me what i'm looking for
}