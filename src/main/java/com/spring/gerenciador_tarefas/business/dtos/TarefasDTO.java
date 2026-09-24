package com.spring.gerenciador_tarefas.business.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.gerenciador_tarefas.infraestructure.enuns.PrioridadeEnum;
import com.spring.gerenciador_tarefas.infraestructure.enuns.StatusEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TarefasDTO {

    private Long id;
    @NotBlank(message = "O título é obrigatório")
    private String titulo;
    private String descricao;
    private StatusEnum status;
    @NotNull(message = "A prioridade é obrigatória")
    private PrioridadeEnum prioridade;
    @NotNull(message = "A data limite obrigatória")
    @FutureOrPresent(message = "A data limite é obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLimite;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime dataConclusao;
    private Long usuarioId;

}
