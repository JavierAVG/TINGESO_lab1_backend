package com.example.mortgage_backend.controllers;

import com.example.mortgage_backend.entities.LoanEntity;
import com.example.mortgage_backend.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {
    @Autowired
    LoanService loanService;

    @PostMapping("/")
    public ResponseEntity<LoanEntity> saveLoan(@RequestBody LoanEntity loan) {
        LoanEntity new_loan = loanService.saveLoan(loan);
        return ResponseEntity.ok(new_loan);
    }

    @PostMapping("/{id}/{state}")
    public String updateLoanState(@PathVariable Long id, @PathVariable String state) {
        loanService.updateState(id,state);
        return "Updated Loan ID: " + id + " to State: " + state;
    }

    @PutMapping("/")
    public ResponseEntity<LoanEntity> updateLoan(@RequestBody LoanEntity loan) {
        LoanEntity updated_loan = loanService.updateLoan(loan);
        return ResponseEntity.ok(updated_loan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanEntity> getLoanById(@PathVariable Long id) {
        LoanEntity loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/")
    public ResponseEntity<ArrayList<LoanEntity>> getLoans() {
        ArrayList<LoanEntity> loan_list = loanService.getLoans();
        return ResponseEntity.ok(loan_list);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ArrayList<LoanEntity>> getLoansByClientId(@PathVariable Long id) {
        ArrayList<LoanEntity> loan_list = loanService.getLoansByClientId(id);
        return ResponseEntity.ok(loan_list);
    }

    @GetMapping("/{id}/costs")
    public ResponseEntity<List<Integer>> getLoanCostsById(@PathVariable Long id) {
        List<Integer> costs = loanService.getTotalCosts(id);
        return ResponseEntity.ok(costs);
    }

    @GetMapping("/simulate")
    public ResponseEntity<Integer> calculateMonthlyFee(@RequestParam("interest_rate") double interest_rate, @RequestParam("duration") int duration, @RequestParam("amount") int amount) {
        int monthly_fee = loanService.calcMonthlyFee(interest_rate, duration, amount);
        return ResponseEntity.ok(monthly_fee);
    }
}
