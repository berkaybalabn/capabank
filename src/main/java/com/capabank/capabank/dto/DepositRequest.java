package com.capabank.capabank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {
    @Positive(message = "Yatırılacak tutar sıfırdan büyük olmalıdır!")
    private BigDecimal amount;
}



