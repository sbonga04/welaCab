package com.welaCab.Services;

import com.welaCab.Repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {
    @Autowired
    private TwilioService twilioService;



    public String handleMessege(){return "";}
}