package com.welaCab.Controller;

import com.welaCab.Services.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/whatsapp")
public class WhatsappController {
    @Autowired
    private WhatsappService whatsappService;
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> recieveMessage(
            @RequestParam("From") String from,
            @RequestParam("Body") String body) {

        //Messege handling(formating)
        String response = whatsappService.handleNewMessege(from,body);
        String xml = String.format("<Response><Message>%s</Message></Response>", response);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/xml");
        return new ResponseEntity<>(xml, headers,200);

    }


}
