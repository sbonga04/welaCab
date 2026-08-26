package com.welaCab.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    //what would the app need in order to acces twilio remotely
    @Value("${twilio.account.sid}")
    private String accountSID;
    @Value("${twilio.account.authToken}")
    private String authToken;
    @Value("${twilio.whatsapp.number}")
    private String whatsappNumber;

    public void sendMessege(String toPhoneNumber, String messegeBody){
        //start twilio the
        Twilio.init(accountSID,authToken);
        //create message
        Message.creator( new PhoneNumber(toPhoneNumber),
                new PhoneNumber(whatsappNumber),
                messegeBody).create();

    }

}