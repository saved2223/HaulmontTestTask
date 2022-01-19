package com.haulmont.testTask.entity;

import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false)
    private UUID id;

    @Column(name = "limit", nullable = false)
    private Long limit;

    @Column(name = "interest_rate", nullable = false)
    private Short interestRate;

    public Short getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Short interestRate) {
        this.interestRate = interestRate;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Credit credit = (Credit) o;
        return id != null && Objects.equals(id, credit.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}