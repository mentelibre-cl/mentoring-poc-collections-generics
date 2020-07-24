package com.globant.mentoring.model.repository;

import com.globant.mentoring.model.entity.CodeAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeAccountRepository extends JpaRepository<CodeAccountEntity, Long> {
    CodeAccountEntity findById(long id);
}