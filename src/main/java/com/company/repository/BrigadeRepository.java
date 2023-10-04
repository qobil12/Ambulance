package com.company.repository;

import com.company.entity.Brigade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BrigadeRepository extends JpaRepository<Brigade, UUID> {
    Brigade getBrigadeById(UUID id);

    List<Brigade> findAllByIsBusy(Boolean isBusy);
}
