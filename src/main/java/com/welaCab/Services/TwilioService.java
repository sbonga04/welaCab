package com.welaCab.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
    this is the part where the handling of my front end connects with the bot (twilio)

*/
@Service
public class TwilioService {
    @Value("${twilio.accout.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.number}")
    private String fromNumber;

    public void sendMessage(String To,String Body){
        //this is accessing twilio using my credentials
        Twilio.init(accountSid,authToken);
        //write the message and send it to my whatsApp number
        //this is how we create a messege in twilio
        Message.creator(new PhoneNumber(To),
                new PhoneNumber(fromNumber),
                Body).create();
    }
}
