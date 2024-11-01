package com.example.mortgage_backend.services;

import com.example.mortgage_backend.entities.ClientEntity;
import com.example.mortgage_backend.entities.SavingsAccountEntity;
import com.example.mortgage_backend.repositories.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavingsAccountService {
    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    public void saveSavings(Long idLoan, List<Integer> salaries, List<Integer> withdrawals, List<Integer> deposits){
        for (int i = 0; i < 12; i++) {
            SavingsAccountEntity savingsAccount = new SavingsAccountEntity();
            savingsAccount.setIdLoan(idLoan);
            savingsAccount.setDeposit(deposits.get(i));
            savingsAccount.setWithdrawal(withdrawals.get(i));
            savingsAccount.setSalary(salaries.get(i));
            savingsAccount.setMonthsAgo(i+1);

            savingsAccountRepository.save(savingsAccount);
        }
    }

    public void updateSavings(Long idLoan, List<Long> ids, List<Integer> salaries, List<Integer> withdrawals, List<Integer> deposits){
        for (int i = 0; i < 12; i++) {
            SavingsAccountEntity savingsAccount = new SavingsAccountEntity();
            savingsAccount.setIdLoan(idLoan);
            savingsAccount.setId(ids.get(i));
            savingsAccount.setDeposit(deposits.get(i));
            savingsAccount.setWithdrawal(withdrawals.get(i));
            savingsAccount.setSalary(salaries.get(i));
            savingsAccount.setMonthsAgo(i+1);

            savingsAccountRepository.save(savingsAccount);
        }
    }

    public ArrayList<SavingsAccountEntity> getByIdLoan(Long id){
        return (ArrayList<SavingsAccountEntity>) savingsAccountRepository.findByIdLoan(id);
    }
}
