//package com.company.service;
//
//
//import com.company.entity.SmSMessage;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SmsService {
//
//    @Value("${AC5913c1736f04f94dd1af1a7deed74472}")
//    private String accountSid;
//
//    @Value("${1b087e3aa26e214332cc7c39f0ee5362}")
//    private String authToken;
//
//    public String sendSMS(SmSMessage sms) {
//        Twilio.init(accountSid, authToken);
//
//        Message message = Message.creator(
//                        new com.twilio.type.PhoneNumber(sms.getUser().getPhoneNumber()),
//                        new com.twilio.type.PhoneNumber("+14085331808"),
//                        sms.getBody())
//                .create();
//
//       return "Message sent: "+message;
//    }
//}
