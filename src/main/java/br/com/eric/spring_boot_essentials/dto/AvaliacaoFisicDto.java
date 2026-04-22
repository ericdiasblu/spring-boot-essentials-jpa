package br.com.eric.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AvaliacaoFisicDto {

    @NotNull
    private Integer alunoId;

    @NotNull
    private BigDecimal peso;

    @NotNull
    private BigDecimal altura;

    @NotNull
    private BigDecimal percentualGorduraCorporal;

}
