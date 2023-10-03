//package com.company.controller;
//
//import com.company.service.SmsService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController()
//@RequestMapping("/sms/send/")
//public class SmsController {
//    private final SmsService smsService;
//
//    public SmsController(SmsService smsService) {
//        this.smsService = smsService;
//    }
//    @PostMapping("/send")
//    public ResponseEntity<String> sendSms(@RequestBody SmsDTO dto){
//        return ResponseEntity.ok().body(smsService.sendSMS(dto));
//    }
//}
