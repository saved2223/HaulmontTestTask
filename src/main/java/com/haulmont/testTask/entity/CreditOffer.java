package com.haulmont.testTask.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credit_offer")
public class CreditOffer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false)
    private UUID id;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "credit_id", nullable = false)
    private Credit credit;


    @Column(name = "credit_sum", nullable = false)
    private Long creditSum;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentSchedule> paymentSchedules = new ArrayList<>();

    public List<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }

    public Long getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(Long creditSum) {
        this.creditSum = creditSum;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}