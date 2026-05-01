package com.capabank.capabank.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {
    @Positive(message = "Çekilecek tutar sıfırdan büyük olmalıdır!")
    private BigDecimal amount;
}