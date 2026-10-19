package com.spring.gerenciador_tarefas.business.dtos;


import com.spring.gerenciador_tarefas.infraestructure.enuns.StatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusTarefasDTO {

    @NotNull(message = "Status é obrigatório")
    private StatusEnum status;

}
