package com.haulmont.testTask.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false)
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bank_clients",
            joinColumns = @JoinColumn(name = "bank"),
            inverseJoinColumns = @JoinColumn(name = "clients_id"))
    private Set<Client> clients = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bank_credits",
            joinColumns = @JoinColumn(name = "bank"),
            inverseJoinColumns = @JoinColumn(name = "credits_id"))
    private Set<Credit> credits = new LinkedHashSet<>();

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}