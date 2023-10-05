package com.company.service;

import com.company.dto.AuthDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.UserDTO;
import com.company.entity.SmSMessage;
import com.company.entity.UserEntity;
import com.company.enums.ProfileStatus;
import com.company.exceptions.BadRequestException;
import com.company.exceptions.ItemAlreadyExistsException;
import com.company.exceptions.ItemNotFoundException;
import com.company.mapper.UserMapper;
import com.company.repository.UserRepository;
import com.company.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    private final SmsService smsService;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, SmsService smsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.smsService = smsService;
    }


    public String registration(UserDTO dto) {
        userRepository.findByPhoneNumber(dto.getPhoneNumber()).ifPresent(user -> {
            throw new ItemAlreadyExistsException("User already exists with this phone number !");
        });
        userRepository.findByUsername(dto.getUsername()).ifPresent(userEntity -> {
            throw new ItemAlreadyExistsException("User already exist with this username");
        });
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity user = userRepository.save(userMapper.toUserEntity(dto));

        return JwtUtil.encode(user.getId(), user.getRole());
//return "saved";
    }

    public ProfileDTO login(AuthDTO authDTO) {

        var profile = userRepository.findByPhoneNumber(authDTO.getPhoneNumber())
                .orElseThrow(() -> new ItemNotFoundException("User doesn't exist with this number"));


        if (passwordEncoder.encode(authDTO.getPassword()).equals(profile.getPassword())) {
            throw new BadRequestException("User not found or wrong password");
        }

        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new BadRequestException("No permission");
        }

        ProfileDTO dto = new ProfileDTO();
        dto.setUsername(profile.getUsername());
        dto.setPhoneNumber(profile.getPhoneNumber());
        dto.setJwt(JwtUtil.encode(profile.getId(), profile.getRole()));

        return dto;
    }

}
