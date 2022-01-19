package com.haulmont.testTask.repository;

import com.haulmont.testTask.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {

    Optional<Client> findByFullNameEqualsAndPhoneNumberEqualsAndEmailEqualsAndPassportNumberEquals(String fullName, String phoneNumber, String email, String passportNumber);

}