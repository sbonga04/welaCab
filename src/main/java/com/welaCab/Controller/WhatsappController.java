package com.welaCab.Controller;

import com.welaCab.Services.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whatsapp")
public class WhatsappController {

    @Autowired
    private WhatsappService whatsappService;

    //look more in detail on this part;
    public ResponseEntity<String> receiveMessage(
            @RequestParam("From") String from, @RequestParam("Body") String body){

        String response = whatsappService.handleMessege(from,body);
        String xml = String.format("<Response><Message>%s<Message></Response>",response);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/xml");
        return new ResponseEntity<>(xml,headers,200);
    }

}