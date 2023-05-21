package com.example.demo_send_email.service;

import com.example.demo_send_email.model.Demo;
import com.google.zxing.common.BitMatrix;

import java.util.List;

public interface IEmailService {
    void sendEmail(String email);
    void sendQR(String data, String path);
    byte[] getQRCodeImage();
    void save(Demo demo);
    List<Demo> findAll();
}
