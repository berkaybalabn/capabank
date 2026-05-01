package com.capabank.capabank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransferRequest {
    @NotNull(message = "Alıcı hesap ID'si boş olamaz!")
    private Long toAccountId;
    @Positive(message = "Transfer tutarı sıfırdan büyük olmalıdır!")
    private BigDecimal amount;
}
