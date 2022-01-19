package com.haulmont.testTask.controller;

import com.haulmont.testTask.entity.Client;
import com.haulmont.testTask.service.BankService;
import com.haulmont.testTask.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;
import java.util.UUID;

@Controller
public class ClientController {
    private final ClientService clientService;
    private Client selectedClient;
    private final BankService bankService;

    @Autowired
    public ClientController(ClientService clientService, BankService bankService) {
        this.clientService = clientService;
        this.bankService = bankService;
    }

    @GetMapping(value = "/getClients/{id}")
    public ResponseEntity<Set<Client>> getClients(@PathVariable(name = "id") UUID id) {
        final Set<Client> clients = bankService.getBank(id).getClients();
        return clients != null && !clients.isEmpty()
                ? ResponseEntity.ok(clients)
                : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/updateClient")
    public String updateClient(Model model, @ModelAttribute(name = "client") Client client) {
        this.selectedClient.setEmail(client.getEmail());
        this.selectedClient.setFullName(client.getFullName());
        this.selectedClient.setPassportNumber(client.getPassportNumber());
        this.selectedClient.setPhoneNumber(client.getPhoneNumber());
        clientService.addClient(this.selectedClient);
        model.addAttribute("message", "Информация успешно обновлена");
        model.addAttribute("backPage", "/");
        return "message";
    }

    @GetMapping(value = "/showClients")
    public String showClients(Model model) {
        model.addAttribute("banks", bankService.getAllBanks());
        return "clients/showClientsForUpdate";
    }

    @GetMapping(value = "/updateSelectedClient/{id}")
    public String showUpdateForm(Model model, @PathVariable(name = "id") UUID id) {
        this.selectedClient = clientService.getClient(id);
        model.addAttribute("client", this.selectedClient);
        return "clients/updateClient";
    }
}
