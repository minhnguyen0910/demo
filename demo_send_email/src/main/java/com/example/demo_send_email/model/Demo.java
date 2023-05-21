package com.example.demo_send_email.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
public class Demo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "q_r",columnDefinition = "text")
    String qr;

    public Demo() {
    }

    public Demo(Integer id, String qr) {
        this.id = id;
        this.qr = qr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}
