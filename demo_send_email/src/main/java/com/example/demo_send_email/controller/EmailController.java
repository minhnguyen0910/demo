package com.example.demo_send_email.controller;

import com.example.demo_send_email.model.Demo;
import com.example.demo_send_email.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {
    @Autowired
    IEmailService iEmailService;

    @GetMapping("/send/email")
    public ResponseEntity<?> send() {
        iEmailService.sendEmail("minhn091099@gmail.com");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/create/QR")
    public ResponseEntity<?> create() {
        iEmailService.sendQR("Minh Hello1111", "E:\\codegym\\demo_send_email\\src\\main\\resources\\static\\demo4.png");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Demo>> findAll() {
        return new ResponseEntity<>(iEmailService.findAll(), HttpStatus.OK);
    }
}
