package com.company.service;

import com.company.dto.UserDTO;
import com.company.entity.UserEntity;
import com.company.exceptions.ItemAlreadyExistsException;
import com.company.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registration(UserDTO dto) {

        if(userRepository.existsByPhoneNumber(dto.getPhoneNumber())){
         throw new ItemAlreadyExistsException("Phone number already exists ! Enter another number !");
        }

        UserEntity user=new UserEntity();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());
        userRepository.save(user);

        return "User successfully was registered !";
    }
}
