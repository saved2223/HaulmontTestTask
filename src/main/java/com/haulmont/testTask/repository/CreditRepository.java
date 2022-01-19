package com.haulmont.testTask.repository;

import com.haulmont.testTask.entity.Bank;
import com.haulmont.testTask.entity.Credit;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditRepository extends CrudRepository<Credit, UUID> {

}