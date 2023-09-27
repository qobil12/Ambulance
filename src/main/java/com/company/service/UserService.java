package com.company.service;

import com.company.dto.ChangeUserInfoDTO;
import com.company.dto.UserDTO;
import com.company.exceptions.ItemAlreadyExistsException;
import com.company.exceptions.ItemNotFoundException;
import com.company.mapper.UserMapper;
import com.company.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public UserDTO registration(UserDTO dto) {
        userRepository.findByPhoneNumber(dto.getPhoneNumber()).ifPresent(user -> {
            throw new ItemAlreadyExistsException("User already exists with this phone number !");
        });
//
        var saveUser = userRepository.save(userMapper.toUserEntity(dto));

        return userMapper.toUserDto(saveUser);
    }

    public UserDTO changeUserInfos(ChangeUserInfoDTO dto) {
        var user = userRepository.findById(dto.getId()).orElseThrow(() -> new ItemNotFoundException("Not found"));

        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getSurname() != null) {
            user.setSurname(dto.getSurname());
        }
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        return userMapper.toUserDto(userRepository.save(user));
    }

    public void deleteUser(UUID id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("User doesn't exist with this ID")));
    }
}
