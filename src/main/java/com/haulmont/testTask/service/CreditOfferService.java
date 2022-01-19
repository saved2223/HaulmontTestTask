package com.haulmont.testTask.service;

import com.haulmont.testTask.entity.Client;
import com.haulmont.testTask.entity.Credit;
import com.haulmont.testTask.entity.CreditOffer;
import com.haulmont.testTask.entity.PaymentSchedule;
import com.haulmont.testTask.repository.CreditOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditOfferService {

    private final CreditOfferRepository creditOfferRepository;


    private int creditLength;

    @Autowired
    public CreditOfferService(CreditOfferRepository creditOfferRepository) {
        this.creditOfferRepository = creditOfferRepository;
    }

    public void addCreditOffer(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

    public double getTotalSum(CreditOffer creditOffer) {
        return creditOffer.getCreditSum() +
                creditOffer.getPaymentSchedules().get(
                        creditOffer.getPaymentSchedules().size() - 1
                ).getInterestRepaymentSum();
    }

    public CreditOffer getCreditOffer(Credit credit, Client client, Long creditSum, String startDate, Integer creditLength) {
        this.creditLength = creditLength * 12;
        CreditOffer creditOffer = new CreditOffer();
        if (creditSum <= credit.getLimit()) {
            creditOffer.setCredit(credit);
            creditOffer.setClient(client);
            creditOffer.setCreditSum(creditSum);
            creditOffer.setPaymentSchedules(getPaymentShedule(creditOffer, startDate));
        }
        return creditOffer;
    }

    private List<PaymentSchedule> getPaymentShedule(CreditOffer creditOffer, String startDate) {
        List<PaymentSchedule> lst = new ArrayList<>();
        Date date = Date.valueOf(startDate);
        float percent = (float) creditOffer.getCredit().getInterestRate() / (100 * 12);
        double annuitet = creditOffer.getCreditSum() * (percent / (1 - Math.pow(1 + percent, creditLength * (-1))));
        double interestSum = (annuitet * creditLength) - creditOffer.getCreditSum();
        for (int i = 1; i <= creditLength; i++) {
            double interestRepayment = interestSum / creditLength * i;
            double creditRepayment = (annuitet - interestSum / creditLength) * i;
            PaymentSchedule tmp = new PaymentSchedule();
            tmp.setCreditOffer(creditOffer);
            tmp.setPaymentDate(date);
            tmp.setPaymentSum((float) annuitet);
            tmp.setCreditRepaymentSum(creditRepayment);
            tmp.setInterestRepaymentSum(interestRepayment);
            lst.add(tmp);
            date = Date.valueOf(date.toLocalDate().plusMonths(1));
        }
        return lst;
    }
}
