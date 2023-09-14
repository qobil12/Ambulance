package com.company.repository;

import com.company.entity.Application;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<Application, String> {
    Application getApplicationById(String id);

    @Modifying
    @Transactional
    @Query
    changeApplicationStatus(String id, boolean status) ;


}
