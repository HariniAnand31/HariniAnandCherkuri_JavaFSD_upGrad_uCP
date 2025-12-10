package com.bank.notification_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @PostMapping("/send")
    public String sendNotification(@RequestParam String message) {
        System.out.println("📩 Notification Sent: " + message);
        return "Notification Delivered: " + message;
    }
}
