package com.haulmont.testTask.service;

import com.haulmont.testTask.entity.Credit;
import com.haulmont.testTask.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CreditService {
    private final CreditRepository creditRepository;


    @Autowired
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Transactional
    public void deleteCredit(UUID id) {
        creditRepository.deleteById(id);
    }

    public Credit getCredit(UUID id) {
        return creditRepository.findById(id).get();
    }


}
