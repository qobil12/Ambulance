package com.company.repository;

import com.company.entity.Brigade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrigadeRepository extends CrudRepository<Brigade,String> {
    Brigade getBrigadeById(String id);
    List<Brigade> getBrigadeByIsBusy(Boolean isBusy);
}
