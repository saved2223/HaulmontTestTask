package com.haulmont.testTask.service;

import com.haulmont.testTask.entity.Bank;
import com.haulmont.testTask.entity.Client;
import com.haulmont.testTask.entity.Credit;
import com.haulmont.testTask.repository.BankRepository;
import com.haulmont.testTask.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final CreditRepository creditRepository;

    @Autowired
    public BankService(BankRepository bankRepository, CreditRepository creditRepository){
        this.bankRepository = bankRepository;
        this.creditRepository = creditRepository;
    }

    public Iterable<Bank> getAllBanks(){
        return bankRepository.findAll();
    }

    public void addClientToBank(Credit credit, Client client){
        Bank b = bankRepository.findByCredits_IdEquals(credit.getId());
        b.getClients().add(client);
        bankRepository.save(b);
    }


    public Bank getBank(UUID id){
       return bankRepository.findById(id).get();
    }

    public void addBank(Bank bank){
        bankRepository.save(bank);
    }

    public void addLoan(UUID bankId, Credit credit){
        bankRepository.findById(bankId).ifPresent(bank -> {
            creditRepository.save(credit);
            bank.getCredits().add(credit);
            bankRepository.save(bank);
        });
    }
}
