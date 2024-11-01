package com.example.mortgage_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Long idClient;

    // ==================== LOAN =====================
    private String type;        // First home - Second home - Commercial properties - Remodeling
    private int duration;                       // in years
    private double interestRate;               // annual
    private int amount;
    private int monthlyFee;

    // ================== DOCUMENTS ==================
    private String docIncomeProof;                // comprobante de ingresos
    private String docAppraisalCertificate;       // certificado de avaluo
    private String docCreditHistory;              // historial crediticio
    private String docFirstHousingDeed;          // escritura de la primera vivienda
    private String docBusinessFinancialState;    // estado financiero del negocio
    private String docBusinessPlan;               // plan de negocios
    private String docRemodelingBudget;           // presupuesto de remodelacion
    private String docDicomHistory;

    // =================== REQUEST ===================
    private Date issueDate;
    private int monthlyIncome;
    private boolean delinquencyStatus;         // DICOM
    private int employmentLongevity;
    private int totalDebt;
    private int propertyValue;
    private int accumulatedSalary;
    private int savingsAccountLongevity;
    private String faultDescriptor;
    private String state;       // Documentation pending - In evaluation - Pre-approved - Approved - Rejected - Canceled
}
