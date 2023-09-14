package com.company.repository;

import com.company.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <UserEntity,String> {

     boolean existsByPhoneNumber(String phoneNumber);
     UserEntity getById(String id);

}
