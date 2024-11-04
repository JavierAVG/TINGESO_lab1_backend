package com.example.mortgage_backend.services;

import com.example.mortgage_backend.entities.LoanEntity;
import com.example.mortgage_backend.repositories.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LoanServiceTest {
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;
    private LoanEntity loan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loan = new LoanEntity();
        loan.setId(1L);
        loan.setIdClient(1L);
        loan.setType("First home");
        loan.setDuration(20);
        loan.setInterestRate(4.5);
        loan.setAmount(100000000);
        loan.setMonthlyFee(632649);
        loan.setMonthlyIncome(700000);
        loan.setEmploymentLongevity(15);
        loan.setTotalDebt(0);
        loan.setPropertyValue(390000000);
        loan.setAccumulatedSalary(125000000);
        loan.setSavingsAccountLongevity(8);
        loan.setState("In evaluation");
    }

    @Test
    void whenSaveLoan_thenCorrect() {
        // Given
        loan.setIdClient(1L);
        loan.setType("First home");
        loan.setDuration(20);
        loan.setInterestRate(4.5);
        loan.setAmount(100000000);
        loan.setMonthlyFee(632649);
        loan.setMonthlyIncome(700000);
        loan.setEmploymentLongevity(15);
        loan.setTotalDebt(0);
        loan.setPropertyValue(390000000);
        loan.setAccumulatedSalary(125000000);
        loan.setSavingsAccountLongevity(8);
        loan.setState("In evaluation");

        when(loanRepository.save(loan)).thenReturn(loan);

        // When
        LoanEntity returnedLoan = loanService.saveLoan(loan);

        // Then
        assertThat(returnedLoan.getIdClient()).isEqualTo(1L);
        assertThat(returnedLoan.getType()).isEqualTo("First home");
        assertThat(returnedLoan.getDuration()).isEqualTo(20);
        assertThat(returnedLoan.getInterestRate()).isEqualTo(4.5);
        assertThat(returnedLoan.getAmount()).isEqualTo(100000000);
        assertThat(returnedLoan.getMonthlyFee()).isEqualTo(632649);
        assertThat(returnedLoan.getMonthlyIncome()).isEqualTo(700000);
        assertThat(returnedLoan.getEmploymentLongevity()).isEqualTo(15);
        assertThat(returnedLoan.getTotalDebt()).isEqualTo(0);
        assertThat(returnedLoan.getPropertyValue()).isEqualTo(390000000);
        assertThat(returnedLoan.getAccumulatedSalary()).isEqualTo(125000000);
        assertThat(returnedLoan.getSavingsAccountLongevity()).isEqualTo(8);
        assertThat(returnedLoan.getState()).isEqualTo("In evaluation");
    }

    @Test
    void whenUpdateLoan_thenCorrect() {
        // Given
        LoanEntity updatedLoan = new LoanEntity();
        loan.setId(1L);                     // <- same ID (important)
        updatedLoan.setIdClient(1L);
        updatedLoan.setType("Second home");
        updatedLoan.setDuration(20);
        updatedLoan.setInterestRate(4.5);
        updatedLoan.setAmount(100000000);
        updatedLoan.setMonthlyFee(632649);
        updatedLoan.setMonthlyIncome(5500000);
        updatedLoan.setEmploymentLongevity(16);
        updatedLoan.setTotalDebt(0);
        updatedLoan.setPropertyValue(505000000);
        updatedLoan.setAccumulatedSalary(233000000);
        updatedLoan.setSavingsAccountLongevity(9);
        updatedLoan.setState("Canceled");
        // Mock
        when(loanRepository.save(updatedLoan)).thenReturn(updatedLoan);

        // When
        LoanEntity returnedLoan = loanService.updateLoan(updatedLoan);

        // Then
        assertThat(returnedLoan.getIdClient()).isEqualTo(1L);
        assertThat(returnedLoan.getType()).isEqualTo("Second home");
        assertThat(returnedLoan.getDuration()).isEqualTo(20);
        assertThat(returnedLoan.getInterestRate()).isEqualTo(4.5);
        assertThat(returnedLoan.getAmount()).isEqualTo(100000000);
        assertThat(returnedLoan.getMonthlyFee()).isEqualTo(632649);
        assertThat(returnedLoan.getMonthlyIncome()).isEqualTo(5500000);
        assertThat(returnedLoan.getEmploymentLongevity()).isEqualTo(16);
        assertThat(returnedLoan.getTotalDebt()).isEqualTo(0);
        assertThat(returnedLoan.getPropertyValue()).isEqualTo(505000000);
        assertThat(returnedLoan.getAccumulatedSalary()).isEqualTo(233000000);
        assertThat(returnedLoan.getSavingsAccountLongevity()).isEqualTo(9);
        assertThat(returnedLoan.getState()).isEqualTo("Canceled");
    }

    @Test
    void whenUpdateState_thenCorrect() {
        // Given
        Long loanId = 1L;               // <- state = "In evaluation"
        String newState = "Approved";

        // Mocking findById to return the loan
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        // Mocking save to return the updated loan
        when(loanRepository.save(loan)).thenReturn(loan);

        // When
        String returnedState = loanService.updateState(loanId, newState);
        LoanEntity updatedLoan = loanService.getLoanById(loanId);

        // Then
        assertThat(returnedState).isEqualTo("Approved");
        assertThat(updatedLoan.getState()).isEqualTo("Approved"); // Check that the loan's state was updated
    }

    @Test
    void whenGetLoanById_thenCorrect() {
        // Given
        Long loanId = 1L;

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        // When
        LoanEntity returnedLoan = loanService.getLoanById(loanId);

        // Then
        assertThat(returnedLoan.getId()).isEqualTo(1L);
        assertThat(returnedLoan.getIdClient()).isEqualTo(1L);
        assertThat(returnedLoan.getType()).isEqualTo("First home");
        assertThat(returnedLoan.getDuration()).isEqualTo(20);
        assertThat(returnedLoan.getInterestRate()).isEqualTo(4.5);
        assertThat(returnedLoan.getAmount()).isEqualTo(100000000);
        assertThat(returnedLoan.getMonthlyFee()).isEqualTo(632649);
        assertThat(returnedLoan.getMonthlyIncome()).isEqualTo(700000);
        assertThat(returnedLoan.getEmploymentLongevity()).isEqualTo(15);
        assertThat(returnedLoan.getTotalDebt()).isEqualTo(0);
        assertThat(returnedLoan.getPropertyValue()).isEqualTo(390000000);
        assertThat(returnedLoan.getAccumulatedSalary()).isEqualTo(125000000);
        assertThat(returnedLoan.getSavingsAccountLongevity()).isEqualTo(8);
        assertThat(returnedLoan.getState()).isEqualTo("In evaluation");
    }

    @Test
    void whenGetLoans_thenCorrect() {
        // Given
        LoanEntity loan1 = new LoanEntity();
        loan1.setId(1L);
        loan1.setIdClient(1L);
        loan1.setType("First home");
        loan1.setDuration(20);
        loan1.setInterestRate(4.5);
        loan1.setAmount(100000000);
        loan1.setMonthlyFee(632649);
        loan1.setMonthlyIncome(700000);
        loan1.setEmploymentLongevity(15);
        loan1.setTotalDebt(0);
        loan1.setPropertyValue(390000000);
        loan1.setAccumulatedSalary(125000000);
        loan1.setSavingsAccountLongevity(8);
        loan1.setState("In evaluation");

        LoanEntity loan2 = new LoanEntity();
        loan2.setId(2L);
        loan2.setIdClient(1L);
        loan2.setType("Second home");
        loan2.setDuration(20);
        loan2.setInterestRate(4.5);
        loan2.setAmount(100000000);
        loan2.setMonthlyFee(632649);
        loan2.setMonthlyIncome(5500000);
        loan2.setEmploymentLongevity(16);
        loan2.setTotalDebt(0);
        loan2.setPropertyValue(505000000);
        loan2.setAccumulatedSalary(233000000);
        loan2.setSavingsAccountLongevity(9);
        loan2.setState("Canceled");

        List<LoanEntity> mockLoanList = new ArrayList<>();
        mockLoanList.add(loan1);
        mockLoanList.add(loan2);
        // Mock
        when(loanRepository.findAll()).thenReturn(mockLoanList);

        // When
        ArrayList<LoanEntity> returnedLoans = loanService.getLoans();

        // Then
        assertThat(returnedLoans).isNotNull();
        assertThat(returnedLoans.size()).isEqualTo(2);

        assertThat(returnedLoans.get(0).getIdClient()).isEqualTo(1L);
        assertThat(returnedLoans.get(0).getType()).isEqualTo("First home");
        assertThat(returnedLoans.get(0).getDuration()).isEqualTo(20);
        assertThat(returnedLoans.get(0).getInterestRate()).isEqualTo(4.5);
        assertThat(returnedLoans.get(0).getAmount()).isEqualTo(100000000);
        assertThat(returnedLoans.get(0).getMonthlyFee()).isEqualTo(632649);
        assertThat(returnedLoans.get(0).getMonthlyIncome()).isEqualTo(700000);
        assertThat(returnedLoans.get(0).getEmploymentLongevity()).isEqualTo(15);
        assertThat(returnedLoans.get(0).getTotalDebt()).isEqualTo(0);
        assertThat(returnedLoans.get(0).getPropertyValue()).isEqualTo(390000000);
        assertThat(returnedLoans.get(0).getAccumulatedSalary()).isEqualTo(125000000);
        assertThat(returnedLoans.get(0).getSavingsAccountLongevity()).isEqualTo(8);
        assertThat(returnedLoans.get(0).getState()).isEqualTo("In evaluation");

        assertThat(returnedLoans.get(1).getIdClient()).isEqualTo(1L);
        assertThat(returnedLoans.get(1).getType()).isEqualTo("Second home");
        assertThat(returnedLoans.get(1).getDuration()).isEqualTo(20);
        assertThat(returnedLoans.get(1).getInterestRate()).isEqualTo(4.5);
        assertThat(returnedLoans.get(1).getAmount()).isEqualTo(100000000);
        assertThat(returnedLoans.get(1).getMonthlyFee()).isEqualTo(632649);
        assertThat(returnedLoans.get(1).getMonthlyIncome()).isEqualTo(5500000);
        assertThat(returnedLoans.get(1).getEmploymentLongevity()).isEqualTo(16);
        assertThat(returnedLoans.get(1).getTotalDebt()).isEqualTo(0);
        assertThat(returnedLoans.get(1).getPropertyValue()).isEqualTo(505000000);
        assertThat(returnedLoans.get(1).getAccumulatedSalary()).isEqualTo(233000000);
        assertThat(returnedLoans.get(1).getSavingsAccountLongevity()).isEqualTo(9);
        assertThat(returnedLoans.get(1).getState()).isEqualTo("Canceled");
    }

    @Test
    void getLoansByClientId_thenCorrect() {
        // Given
        Long clientId = 1L;

        LoanEntity loan1 = new LoanEntity();
        loan1.setId(1L);
        loan1.setIdClient(1L);                  // <- same client id
        loan1.setType("First home");
        loan1.setDuration(20);
        loan1.setInterestRate(4.5);
        loan1.setAmount(100000000);
        loan1.setMonthlyFee(632649);
        loan1.setMonthlyIncome(700000);
        loan1.setEmploymentLongevity(15);
        loan1.setTotalDebt(0);
        loan1.setPropertyValue(390000000);
        loan1.setAccumulatedSalary(125000000);
        loan1.setSavingsAccountLongevity(8);
        loan1.setState("In evaluation");

        LoanEntity loan2 = new LoanEntity();
        loan2.setId(2L);
        loan2.setIdClient(1L);                  // <- same client id
        loan2.setType("Second home");
        loan2.setDuration(20);
        loan2.setInterestRate(4.5);
        loan2.setAmount(100000000);
        loan2.setMonthlyFee(632649);
        loan2.setMonthlyIncome(5500000);
        loan2.setEmploymentLongevity(16);
        loan2.setTotalDebt(0);
        loan2.setPropertyValue(505000000);
        loan2.setAccumulatedSalary(233000000);
        loan2.setSavingsAccountLongevity(9);
        loan2.setState("Canceled");

        LoanEntity loan3 = new LoanEntity();
        loan3.setId(3L);
        loan3.setIdClient(3L);                  // <- different client id
        loan3.setType("Remodeling");
        loan3.setDuration(20);
        loan3.setInterestRate(4.5);
        loan3.setAmount(100000000);
        loan3.setMonthlyFee(632649);
        loan3.setMonthlyIncome(1200000);
        loan3.setEmploymentLongevity(9);
        loan3.setTotalDebt(150000);
        loan3.setPropertyValue(6070000);
        loan3.setAccumulatedSalary(599000000);
        loan3.setSavingsAccountLongevity(4);
        loan3.setState("Approved");

        List<LoanEntity> mockLoanList = new ArrayList<>();
        mockLoanList.add(loan1);
        mockLoanList.add(loan2);
        // Mock
        when(loanRepository.findByIdClient(clientId)).thenReturn(mockLoanList);

        // When
        ArrayList<LoanEntity> returnedLoans = loanService.getLoansByClientId(clientId);

        // Then
        assertThat(returnedLoans).isNotNull();
        assertThat(returnedLoans.size()).isEqualTo(2);

        assertThat(returnedLoans.get(0).getIdClient()).isEqualTo(1L);
        assertThat(returnedLoans.get(0).getType()).isEqualTo("First home");
        assertThat(returnedLoans.get(0).getDuration()).isEqualTo(20);
        assertThat(returnedLoans.get(0).getInterestRate()).isEqualTo(4.5);
        assertThat(returnedLoans.get(0).getAmount()).isEqualTo(100000000);
        assertThat(returnedLoans.get(0).getMonthlyFee()).isEqualTo(632649);
        assertThat(returnedLoans.get(0).getMonthlyIncome()).isEqualTo(700000);
        assertThat(returnedLoans.get(0).getEmploymentLongevity()).isEqualTo(15);
        assertThat(returnedLoans.get(0).getTotalDebt()).isEqualTo(0);
        assertThat(returnedLoans.get(0).getPropertyValue()).isEqualTo(390000000);
        assertThat(returnedLoans.get(0).getAccumulatedSalary()).isEqualTo(125000000);
        assertThat(returnedLoans.get(0).getSavingsAccountLongevity()).isEqualTo(8);
        assertThat(returnedLoans.get(0).getState()).isEqualTo("In evaluation");

        assertThat(returnedLoans.get(1).getIdClient()).isEqualTo(1L);
        assertThat(returnedLoans.get(1).getType()).isEqualTo("Second home");
        assertThat(returnedLoans.get(1).getDuration()).isEqualTo(20);
        assertThat(returnedLoans.get(1).getInterestRate()).isEqualTo(4.5);
        assertThat(returnedLoans.get(1).getAmount()).isEqualTo(100000000);
        assertThat(returnedLoans.get(1).getMonthlyFee()).isEqualTo(632649);
        assertThat(returnedLoans.get(1).getMonthlyIncome()).isEqualTo(5500000);
        assertThat(returnedLoans.get(1).getEmploymentLongevity()).isEqualTo(16);
        assertThat(returnedLoans.get(1).getTotalDebt()).isEqualTo(0);
        assertThat(returnedLoans.get(1).getPropertyValue()).isEqualTo(505000000);
        assertThat(returnedLoans.get(1).getAccumulatedSalary()).isEqualTo(233000000);
        assertThat(returnedLoans.get(1).getSavingsAccountLongevity()).isEqualTo(9);
        assertThat(returnedLoans.get(1).getState()).isEqualTo("Canceled");
    }

    @Test
    void whenCalcMonthlyFee_thenCorrect() {
        //Given
        double annual_interest = 4.5;
        int duration_in_years = 20;
        int amount = 100000000;

        //When
        int monthlyFee = loanService.calcMonthlyFee(annual_interest, duration_in_years, amount);

        //Then
        assertThat(monthlyFee).isEqualTo(632649);
    }

    @Test
    void whenGetTotalCosts_thenCorrect() {
        // Given
        Long loanId = 1L;

        List<Integer> costsList = new ArrayList<>();
        costsList.add(632649);
        costsList.add(30000);
        costsList.add(20000);
        costsList.add(1000000);
        costsList.add(682649);
        costsList.add(152835760);
        // Mock
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        // When
        List<Integer> returnedList = loanService.getTotalCosts(1L);

        // Then
        assertThat(returnedList).isEqualTo(costsList);
    }
}
