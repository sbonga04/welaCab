package com.welaCab.controller;

import com.welaCab.service.WhatsAppService;
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

public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> recieveMessage(
            @RequestParam("From") String from,
            @RequestParam("Body") String body) {

        String response = whatsAppService.handleMessage(from, body);
        String xml = String.format("<Response><Message>%s</Message></Response>", response);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/xml");

        return new ResponseEntity<>(xml, headers, 200);
    }

}
