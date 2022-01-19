package com.haulmont.testTask.repository;

import com.haulmont.testTask.entity.CreditOffer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditOfferRepository extends CrudRepository<CreditOffer, UUID> {
}