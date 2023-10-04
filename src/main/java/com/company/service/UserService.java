package com.company.service;

import com.company.dto.ChangeUserInfoDTO;
import com.company.dto.UserDTO;
import com.company.entity.UserEntity;
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
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if (dto.getUsername() != null) {
            userRepository.findByUsername(dto.getUsername()).ifPresent(userEntity -> {
                throw new ItemAlreadyExistsException("User already exist with this username !");
            });
            user.setUsername(dto.getName());
        }

        return userMapper.toUserDto(userRepository.save(user));
    }

    public void deleteUser(UUID id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("User doesn't exist with this ID")));
    }

    public UserEntity getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("User doesn't exist with this ID"));
    }
}
