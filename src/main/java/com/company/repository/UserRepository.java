package com.company.repository;

import com.company.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

     boolean existsByPhoneNumber(String phoneNumber);

}
