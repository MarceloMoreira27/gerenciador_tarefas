package com.spring.gerenciador_tarefas.infraestructure.entity;


import com.spring.gerenciador_tarefas.infraestructure.enuns.PrioridadeEnum;
import com.spring.gerenciador_tarefas.infraestructure.enuns.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tarefas")
public class TarefasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private StatusEnum status;
    @Enumerated(EnumType.STRING)
    @Column(name = "prioridade",nullable = false, length = 50)
    private PrioridadeEnum prioridade;
    @Column(name = "data_limite")
    private LocalDate dataLimite;
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

}
