package com.haulmont.testTask.controller;

import com.haulmont.testTask.entity.Bank;
import com.haulmont.testTask.entity.Credit;
import com.haulmont.testTask.service.BankService;
import com.haulmont.testTask.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@Controller
public class BanksController {
    private final BankService bankService;
    private final CreditService creditService;

    @Autowired
    public BanksController(BankService bankService, CreditService creditService) {
        this.bankService = bankService;
        this.creditService = creditService;
    }


    @GetMapping(value = "/loans/{flag}") //1 chose 2 delete
    public String showBanksAndLoans(Model model, @PathVariable(name = "flag") int flag) {
        model.addAttribute("banks", bankService.getAllBanks());
        if (flag == 2) {
            return "banks/showBanksForDelete";
        } else return "banks/showBanksForChose";
    }

    @GetMapping(value = "/getLoans/{id}")
    public ResponseEntity<Set<Credit>> getLoans(@PathVariable(name = "id") UUID id) {
        final Set<Credit> loans = bankService.getBank(id).getCredits();
        return loans != null && !loans.isEmpty()
                ? ResponseEntity.ok(loans)
                : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/addBank")
    public String showPageForAddingBank(Model model) {
        Bank bank = new Bank();
        model.addAttribute("bank", bank);
        return "banks/addBank";
    }

    @PostMapping(value = "/addBank")
    public String addNewBank(Model model, @ModelAttribute("bank") Bank bank) {
        bankService.addBank(bank);
        model.addAttribute("message", "Успешно добавлено");
        model.addAttribute("backPage", "/");
        return "message";
    }

    @GetMapping(value = "/deleteLoan/{id}")
    public String showBanksForDelete(Model model, @PathVariable(name = "id") UUID creditId) {
        creditService.deleteCredit(creditId);
        model.addAttribute("message", "Кредит успешно удален");
        model.addAttribute("backPage", "/");
        return "message";
    }

    @GetMapping(value = "/addLoan")
    public String showPageForAddingLoan(Model model) {
        model.addAttribute("loan", new Credit());
        model.addAttribute("banks", bankService.getAllBanks());
        return "banks/addLoan";
    }

    @PostMapping(value = "/addLoan")
    public String addNewLoan(Model model, @RequestParam(name = "bank") UUID bankId, @ModelAttribute("loan") Credit credit) {
        bankService.addLoan(bankId, credit);
        model.addAttribute("message", "Успешно добавлено");
        model.addAttribute("backPage", "/");
        return "message";
    }
}
