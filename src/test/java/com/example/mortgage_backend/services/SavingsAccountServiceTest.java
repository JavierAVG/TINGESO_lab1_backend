package com.example.mortgage_backend.services;

import com.example.mortgage_backend.entities.SavingsAccountEntity;
import com.example.mortgage_backend.repositories.SavingsAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SavingsAccountServiceTest {
    @Mock
    private SavingsAccountRepository savingsAccountRepository;

    @InjectMocks
    private SavingsAccountService savingsAccountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetByIdLoan_thenCorrect() {
        // Given
        Long loanId = 1L;

        SavingsAccountEntity savings1 = new SavingsAccountEntity();
        savings1.setIdLoan(loanId);
        savings1.setMonthsAgo(1);
        savings1.setWithdrawal(1);
        savings1.setDeposit(2);
        savings1.setSalary(3);

        SavingsAccountEntity savings2 = new SavingsAccountEntity();
        savings2.setIdLoan(loanId);
        savings2.setMonthsAgo(2);
        savings2.setWithdrawal(4);
        savings2.setDeposit(5);
        savings2.setSalary(6);

        List<SavingsAccountEntity> mockAccountList = new ArrayList<>();
        mockAccountList.add(savings1);
        mockAccountList.add(savings2);

        // Mocking the repository call
        when(savingsAccountRepository.findByIdLoan(loanId)).thenReturn(mockAccountList);

        // When
        ArrayList<SavingsAccountEntity> returnedSavings = savingsAccountService.getByIdLoan(loanId);

        // Then
        assertThat(returnedSavings).isNotNull();
        assertThat(returnedSavings.size()).isEqualTo(2);

        assertThat(returnedSavings.get(0).getIdLoan()).isEqualTo(loanId);
        assertThat(returnedSavings.get(0).getMonthsAgo()).isEqualTo(1);
        assertThat(returnedSavings.get(0).getWithdrawal()).isEqualTo(1);
        assertThat(returnedSavings.get(0).getDeposit()).isEqualTo(2);
        assertThat(returnedSavings.get(0).getSalary()).isEqualTo(3);

        assertThat(returnedSavings.get(1).getIdLoan()).isEqualTo(loanId);
        assertThat(returnedSavings.get(1).getMonthsAgo()).isEqualTo(2);
        assertThat(returnedSavings.get(1).getWithdrawal()).isEqualTo(4);
        assertThat(returnedSavings.get(1).getDeposit()).isEqualTo(5);
        assertThat(returnedSavings.get(1).getSalary()).isEqualTo(6);
    }

    @Test
    void whenSaveSavings_thenCorrect() {
        // Given
        Long loanId = 1L;
        List<Integer> salaries = Arrays.asList(3000, 3200, 3100, 3000, 3000, 3000, 3200, 3100, 3000, 3000, 3000, 3200);
        List<Integer> withdrawals = Arrays.asList(500, 600, 550, 500, 500, 600, 550, 500, 500, 600, 550, 500);
        List<Integer> deposits = Arrays.asList(1000, 1200, 1100, 1000, 1100, 1200, 1100, 1000, 1000, 1200, 1100, 1000);

        // When
        savingsAccountService.saveSavings(loanId, salaries, withdrawals, deposits);

        // Then
        // Verify that savingsAccountRepository.save() was called 12 times
        verify(savingsAccountRepository, times(12)).save(org.mockito.ArgumentMatchers.any(SavingsAccountEntity.class));
    }

    @Test
    void whenUpdateSavings_thenCorrect() {
        // Given
        Long loanId = 1L;
        List<Long> ids = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L);
        List<Integer> salaries = Arrays.asList(3000, 3200, 3100, 3000, 3000, 3000, 3200, 3100, 3000, 3000, 3000, 3200);
        List<Integer> withdrawals = Arrays.asList(500, 600, 550, 500, 500, 600, 550, 500, 500, 600, 550, 500);
        List<Integer> deposits = Arrays.asList(1000, 1200, 1100, 1000, 1100, 1200, 1100, 1000, 1000, 1200, 1100, 1000);

        // When
        savingsAccountService.updateSavings(loanId, ids, salaries, withdrawals, deposits);

        // Then
        // Verify that savingsAccountRepository.save() was called 12 times
        verify(savingsAccountRepository, times(12)).save(org.mockito.ArgumentMatchers.any(SavingsAccountEntity.class));
    }

}
