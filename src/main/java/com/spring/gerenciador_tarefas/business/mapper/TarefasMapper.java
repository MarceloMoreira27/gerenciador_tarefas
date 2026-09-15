package com.spring.gerenciador_tarefas.business.mapper;

import com.spring.gerenciador_tarefas.business.dtos.TarefasDTO;
import com.spring.gerenciador_tarefas.infraestructure.entity.Tarefas;
import org.springframework.stereotype.Component;

@Component
public class TarefasMapper {

    public Tarefas paraTarefas(TarefasDTO dto){
        return Tarefas.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .status(dto.getStatus())
                .prioridade(dto.getPrioridade())
                .dataLimite(dto.getDataLimite())
                .dataCriacao(dto.getDataCriacao())
                .dataConclusao(dto.getDataConclusao())
                .build();

    };


    public TarefasDTO paraTarefasDTO(Tarefas entity){
        return TarefasDTO.builder()
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .status(entity.getStatus())
                .prioridade(entity.getPrioridade())
                .dataLimite(entity.getDataLimite())
                .dataCriacao(entity.getDataCriacao())
                .dataConclusao(entity.getDataConclusao())
                .build();

    };










}
