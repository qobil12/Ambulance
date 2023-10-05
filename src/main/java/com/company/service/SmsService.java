package com.company.service;


import com.company.dto.SmsDTO;
import com.company.entity.SmSMessage;
import com.company.entity.UserEntity;
import com.company.enums.ProfileStatus;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.SmsRepository;
import com.company.repository.UserRepository;
import com.company.util.JwtUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class SmsService {
    private final UserRepository userRepository;
    private final SmsRepository smsRepository;
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${spring.server.url}")
    private String serverUrl;
    @Value("${twilio.phone.number}")
    private String phoneNumber;

    public SmsService(UserRepository userRepository, SmsRepository smsRepository) {
        this.userRepository = userRepository;
        this.smsRepository = smsRepository;
    }

    public String sendSMS(String token) {
        Twilio.init(accountSid, authToken);
        UUID decode = JwtUtil.decode(token);
        UserEntity user = userRepository.findById(decode).orElseThrow(() -> new ItemNotFoundException("User doesn't exist with this ID"));
//        String url = String.format("<a href='%s/api/v1/sms/verification/%s'>Verification Link</a>",
//                serverUrl, token);
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("<h1 style='align-text:center'>Assalomu aleykum\nSizning youtube dan " +
//                "registratsiyadan o'tish uchun linkni ustiga bosing</h1>");
//        builder.append("<p>");
//        builder.append(url);
//        builder.append("</p>");
        Random random = new Random();
        // Generate a random 6-digit number (between 100000 and 999999)
        int generatedCode = random.nextInt(900000) + 100000;

        Message.creator(
                        new com.twilio.type.PhoneNumber(user.getPhoneNumber()),
                        new com.twilio.type.PhoneNumber(phoneNumber),
                        String.valueOf(generatedCode))
                .create();
        SmSMessage smSMessage = new SmSMessage();
        smSMessage.setUser(user);
        smSMessage.setCreatedDate(LocalDateTime.now());
        smSMessage.setGeneratedCode(generatedCode);
        smsRepository.save(smSMessage);
//        SmsDTO dto=new SmsDTO();
//        dto.setToken(token);
//        dto.setVerificationPassword(generatedCode);
        return "Sizga virifikatsiya uchun sms habarnoma jo'natildi ";
    }

    public String changeStatusByVerification(SmsDTO dto) {
        UUID decode = JwtUtil.decode(dto.getToken());

        SmSMessage smSMessage = smsRepository.findByUserIdAndGeneratedCode(decode, dto.getVerificationPassword())
                .orElseThrow(() -> new ItemNotFoundException("Sms doesn't exist with this User ID or Password is incorrect"));
        UserEntity user = userRepository.findById(decode).orElseThrow(() -> new ItemNotFoundException("User doesn't exist with this ID"));
        LocalDateTime currentTime = LocalDateTime.now();
        if (!smSMessage.getCreatedDate().plusMinutes(5).isBefore(currentTime)
                && (dto.getVerificationPassword() == smSMessage.getGeneratedCode())) {

            user.setStatus(ProfileStatus.ACTIVE);
            userRepository.save(user);
            return "You were successfully verified";
        }

        return "Your sms lifetime is expired.\n" +
                "New sms send to your phone number";

    }

    public String resendSms(String token) {
        UUID decode = JwtUtil.decode(token);
        UserEntity user = userRepository.findById(decode).orElseThrow(() -> new ItemNotFoundException("User doesn't exist with this ID"));
       // String encodedJwt = JwtUtil.encode(decode, user.getRole());
        sendSMS(token);
        return "Sizga qaytadan verifikatsiya uchun kod jo'natildi";
    }
}
