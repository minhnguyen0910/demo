package com.example.demo_send_email.repository;

import com.example.demo_send_email.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<Demo,Integer> {
}
