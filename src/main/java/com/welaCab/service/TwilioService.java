package com.welaCab.service;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import static org.aspectj.bridge.Message.*;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.number}")
    private String fromNumber;

    public void sendMessage(String To,String body){
        System.out.println("SID: " + accountSid);
        System.out.println("Token: " + authToken);
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(To),
                new PhoneNumber(fromNumber),
                body
        ).create();
    }
}
