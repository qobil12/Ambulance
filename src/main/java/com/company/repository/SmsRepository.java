package com.company.repository;

import com.company.entity.SmSMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SmsRepository extends JpaRepository<SmSMessage, UUID> {
    Optional<SmSMessage> findByUserIdAndGeneratedCode(UUID userId,int code);

}
