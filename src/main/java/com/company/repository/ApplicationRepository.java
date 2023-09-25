package com.company.repository;

import com.company.entity.Application;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
       List<Application> findAllByIsClosed(Boolean isClosed);

    @Modifying
    @Transactional
    @Query("update Application set isClosed=?1 where id=?1")
    void changeApplicationStatus( Boolean status, UUID id) ;

}
