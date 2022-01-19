package com.haulmont.testTask.controller;

import com.haulmont.testTask.entity.Client;
import com.haulmont.testTask.entity.Credit;
import com.haulmont.testTask.entity.CreditOffer;
import com.haulmont.testTask.service.BankService;
import com.haulmont.testTask.service.ClientService;
import com.haulmont.testTask.service.CreditOfferService;
import com.haulmont.testTask.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class GettingLoanController {
    private final ClientService clientService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;
    private final BankService bankService;

    private Credit currentCredit;
    private CreditOffer currentCreditOffer;

    @Autowired
    public GettingLoanController(ClientService clientService, CreditService creditService,
                                 CreditOfferService creditOfferService, BankService bankService) {
        this.clientService = clientService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
        this.bankService = bankService;
    }

    @GetMapping(value = "/choseLoan/{id}")
    public String showFieldsToGetLoan(@PathVariable(name = "id") UUID creditId) {
        this.currentCredit = creditService.getCredit(creditId);
        return "loans/choseSum";
    }

    @GetMapping(value = "/choseSum")
    public String choseSum() {
        return "loans/choseSum";
    }

    @PostMapping(value = "/offer")
    public String readSumAndLength(Model model, @RequestParam(name = "sum") Long creditSum,
                                   @RequestParam(name = "date") String date,
                                   @RequestParam(name = "length") Integer length) {
        this.currentCreditOffer = creditOfferService.getCreditOffer(this.currentCredit, new Client(), creditSum, date, length);
        model.addAttribute("creditOffer", this.currentCreditOffer);
        model.addAttribute("totalSum", creditOfferService.getTotalSum(this.currentCreditOffer));
        return "loans/creditOffer";
    }

    @GetMapping(value = "/commitLoan")
    public String showFormAddClient(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "clients/addClient";
    }

    @PostMapping(value = "/commitLoan")
    public String addNewClient(Model model, @ModelAttribute("client") Client client) {
        this.currentCreditOffer.setClient(client);
        clientService.addClient(client);
        creditOfferService.addCreditOffer(this.currentCreditOffer);
        bankService.addClientToBank(this.currentCredit, client);
        model.addAttribute("message", "Успешно добавлено");
        model.addAttribute("backPage", "/");
        return "message";
    }

}
