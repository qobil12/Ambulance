package com.company.repository;

import com.company.entity.Application;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ApplicationRepository extends CrudRepository<Application, String> {
    Application getApplicationById(String id);


    List<Application> findAllByIsClosed(Boolean isClosed);

    @Modifying
    @Transactional
    @Query("update Application set isClosed=?1 where id=?1")
    void changeApplicationStatus( Boolean status, String id) ;

    Iterable<Application> findAll();

}
