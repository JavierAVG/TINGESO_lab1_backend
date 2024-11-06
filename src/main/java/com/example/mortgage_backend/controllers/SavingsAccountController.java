package com.example.mortgage_backend.controllers;

import com.example.mortgage_backend.entities.SavingsAccountEntity;
import com.example.mortgage_backend.services.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/savingsaccount")
public class SavingsAccountController {
    @Autowired
    SavingsAccountService savingsAccountService;

    @PostMapping("/")
    public ResponseEntity<String> saveSavings(@RequestBody Map<String, Object> data) {
        Long idLoan = Long.valueOf(data.get("idloan").toString());
        List<Integer> deposits = (List<Integer>) data.get("deposit");
        List<Integer> withdrawals = (List<Integer>) data.get("withdrawal");
        List<Integer> salaries = (List<Integer>) data.get("salary");

        savingsAccountService.saveSavings(idLoan, salaries, withdrawals, deposits);
        return ResponseEntity.ok("Data received and processed successfully.");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateSavings(@RequestBody Map<String, Object> data) {
        Long idLoan = Long.valueOf(data.get("idloan").toString());
        List<Integer> deposits = (List<Integer>) data.get("deposit");
        List<Integer> withdrawals = (List<Integer>) data.get("withdrawal");
        List<Integer> salaries = (List<Integer>) data.get("salary");
        List<Long> ids = ((List<Integer>) data.get("ids")).stream()
                .map(Integer::longValue)
                .collect(Collectors.toList());

        savingsAccountService.updateSavings(idLoan, ids, salaries, withdrawals, deposits);
        return ResponseEntity.ok("Data received and processed successfully.");
    }

    @GetMapping("/{idloan}")
    public ResponseEntity<ArrayList<SavingsAccountEntity>> getByIdLoan(@PathVariable Long idloan) {
        ArrayList<SavingsAccountEntity> savings = savingsAccountService.getByIdLoan(idloan);
        return ResponseEntity.ok(savings);
    }
}
