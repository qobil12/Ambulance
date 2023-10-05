package com.company.controller;

import com.company.dto.SmsDTO;
import com.company.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/sms/")
public class SmsController {
    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }
    @Operation(summary = "Method for sending sms",description = "By this method application sends verification sms")
    @PostMapping("/verification")
    public ResponseEntity<String> verification (@RequestBody SmsDTO dto){
        return ResponseEntity.ok().body(smsService.changeStatusByVerification(dto));
    }
    @Operation(summary = "Method for sending sms",description = "By this method application sends verification sms")
    @PostMapping("/resend/{token}")
    public ResponseEntity<String> resendSms (@PathVariable String token){
        return ResponseEntity.ok().body(smsService.resendSms(token));
    }

    @Operation(summary = "send sms",description = "Bythis method  sms is sent")
    @PostMapping("/sendsms/{token}")
    public ResponseEntity<String> sendSms(@PathVariable String token){
        return ResponseEntity.ok().body(smsService.sendSMS(token));
    }

}
