package com.haulmont.testTask.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "payment_schedule")
public class PaymentSchedule {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false)
    private UUID id;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name = "payment_sum", nullable = false)
    private Float paymentSum;

    @Column(name = "credit_repayment_sum")
    private Double creditRepaymentSum;

    @Column(name = "interest_repayment_sum")
    private Double interestRepaymentSum;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "credit_offer_id", nullable = false)
    private CreditOffer creditOffer;

    public CreditOffer getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }

    public Double getInterestRepaymentSum() {
        return interestRepaymentSum;
    }

    public void setInterestRepaymentSum(Double interestRepaymentSum) {
        this.interestRepaymentSum = interestRepaymentSum;
    }

    public Double getCreditRepaymentSum() {
        return creditRepaymentSum;
    }

    public void setCreditRepaymentSum(Double creditRepaymentSum) {
        this.creditRepaymentSum = creditRepaymentSum;
    }

    public Float getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(Float paymentSum) {
        this.paymentSum = paymentSum;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}