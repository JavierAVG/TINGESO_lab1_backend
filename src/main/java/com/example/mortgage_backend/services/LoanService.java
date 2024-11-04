package com.example.mortgage_backend.services;

import com.example.mortgage_backend.entities.LoanEntity;
import com.example.mortgage_backend.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;

    // ====================|| CRUD ||
    public LoanEntity saveLoan(LoanEntity loan){
        loan.setMonthlyFee(calcMonthlyFee(loan.getInterestRate(),loan.getDuration(),loan.getAmount()));
        return loanRepository.save(loan);
    }

    public LoanEntity updateLoan(LoanEntity loan){
        loan.setMonthlyFee(calcMonthlyFee(loan.getInterestRate(),loan.getDuration(),loan.getAmount()));
        return loanRepository.save(loan);
    }

    public String updateState(Long id, String state){
        LoanEntity updated_loan = loanRepository.findById(id).get();
        updated_loan.setState(state);
        loanRepository.save(updated_loan);
        return state;
    }

    public LoanEntity getLoanById(Long id){
        return loanRepository.findById(id).get();
    }

    public ArrayList<LoanEntity> getLoans(){
        return (ArrayList<LoanEntity>) loanRepository.findAll();
    }

    public ArrayList<LoanEntity>  getLoansByClientId(Long id){
        return (ArrayList<LoanEntity>) loanRepository.findByIdClient(id);
    }

    // ====================|| P1 || CREDIT SIMULATION ||
    public int calcMonthlyFee(double annual_interest, int duration_in_years, int amount){
        double i = (annual_interest/12.0)/100.0;
        double n = duration_in_years * 12.0;
        return (int) (amount*(i*pow(1.0+i,n))/(pow(1.0+i,n)-1.0));
    }

    // ====================|| P4 || CREDIT EVALUATION ||
    /*
    public List<Integer> evaluateCredit(LoanEntity loan){
        List<Integer> fault_list = new ArrayList<>();

        int monthly_fee = loan.getMonthlyFee();
        int monthly_income = loan.getMonthlyIncome();

        // R1
        if ((monthly_fee/monthly_income)*100 > 35){ fault_list.add(1); }

        return fault_list;
    }
    */

    // ====================|| P6 || TOTAL COSTS ||
    public List<Integer> getTotalCosts(Long id) {
        LoanEntity loan = loanRepository.findById(id).get();

        List<Integer> return_list = new ArrayList<>();
        int loanDuration = loan.getDuration();

        int monthlyFee = loan.getMonthlyFee();
        int insurance1 = (int) Math.round(loan.getAmount()*0.0003);
        int insurance2 = 20000;
        int commission = (int) Math.round(loan.getAmount()*0.01);
        int monthlyCost = monthlyFee + insurance1 + insurance2;
        int totalCost = (monthlyFee * (loanDuration*12)) + commission;

        return_list.add(monthlyFee);
        return_list.add(insurance1);
        return_list.add(insurance2);
        return_list.add(commission);
        return_list.add(monthlyCost);
        return_list.add(totalCost);

        return return_list;
    }
}
