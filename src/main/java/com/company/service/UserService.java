package com.company.service;

import com.company.dto.ChangeUserInfoDTO;
import com.company.dto.UserDTO;
import com.company.entity.UserEntity;
import com.company.exceptions.ItemAlreadyExistsException;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

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

    public String changeUserInfos(ChangeUserInfoDTO dto) {
        Optional<UserEntity> optional = userRepository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ItemNotFoundException("User doesn't exist with this id.");
        }
        UserEntity user = optional.get();
        Stream.of(dto).forEach(d->{
            if(d.getPhoneNumber()!=null){
                user.setPhoneNumber(d.getPhoneNumber());
            }
            if(d.getSurname()!=null){
                user.setSurname(d.getSurname());
            }
            if(d.getName()!=null){
                user.setName(d.getName());
            }
        });
        userRepository.save(user);

        return "User's information changed successfully ";
    }
}
