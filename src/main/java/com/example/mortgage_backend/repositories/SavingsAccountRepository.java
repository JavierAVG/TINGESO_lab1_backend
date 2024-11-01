package com.example.mortgage_backend.repositories;

import com.example.mortgage_backend.entities.SavingsAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccountEntity, Long> {
    List<SavingsAccountEntity> findByIdLoan(Long id);
}
