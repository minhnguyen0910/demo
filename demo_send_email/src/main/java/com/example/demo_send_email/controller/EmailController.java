package com.example.demo_send_email.controller;

import com.example.demo_send_email.model.Demo;
import com.example.demo_send_email.service.IEmailService;
import com.example.demo_send_email.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.util.Arrays;
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
        iEmailService.sendQR("Minh Hello1111", "./src/main/java/com/example/demo_send_email/qr/demo3.png");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("QR")
    public ResponseEntity<byte[]> demoQR() {
        byte[] data = iEmailService.getQRCodeImage();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<Demo>> findAll(){
        return new ResponseEntity<>(iEmailService.findAll(),HttpStatus.OK);
    }
}
