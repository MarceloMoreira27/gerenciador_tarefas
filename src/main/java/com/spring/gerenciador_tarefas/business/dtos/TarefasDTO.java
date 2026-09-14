package com.spring.gerenciador_tarefas.business.dtos;


import com.spring.gerenciador_tarefas.infraestructure.enuns.PrioridadeEnum;
import com.spring.gerenciador_tarefas.infraestructure.enuns.StatusEnum;
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
    private String titulo;
    private String descricao;
    private StatusEnum status;
    private PrioridadeEnum prioridade;
    private LocalDate dataLimite;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

}
