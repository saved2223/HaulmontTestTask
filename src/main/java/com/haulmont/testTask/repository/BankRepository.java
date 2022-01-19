package com.haulmont.testTask.repository;

import com.haulmont.testTask.entity.Bank;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BankRepository extends CrudRepository<Bank, UUID> {
    Bank findByCredits_IdEquals(UUID id);

}