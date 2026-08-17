package com.welaCab.Services;

import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    //what would the app need in order to acces twilio remotely
    private String accounSID;
    private String authToken;
    private String whatsappNumber;

    public void sendMessege(String toPhoneNumber, String messegeBody){

    }

}