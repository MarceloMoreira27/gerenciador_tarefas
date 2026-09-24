package com.spring.gerenciador_tarefas.business.mapper;

import com.spring.gerenciador_tarefas.business.dtos.TarefasDTO;
import com.spring.gerenciador_tarefas.infraestructure.entity.TarefasEntity;
import org.springframework.stereotype.Component;

@Component
public class TarefasMapper {

    public TarefasEntity paraTarefas(TarefasDTO dto){
        return TarefasEntity.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .status(dto.getStatus())
                .prioridade(dto.getPrioridade())
                .dataLimite(dto.getDataLimite())
                .dataCriacao(dto.getDataCriacao())
                .dataConclusao(dto.getDataConclusao())
                .build();

    };


    public TarefasDTO paraTarefasDTO(TarefasEntity entity){
        return TarefasDTO.builder()
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .status(entity.getStatus())
                .prioridade(entity.getPrioridade())
                .dataLimite(entity.getDataLimite())
                .dataCriacao(entity.getDataCriacao())
                .dataConclusao(entity.getDataConclusao())
                .id(entity.getId())
                .usuarioId(entity.getUsuarioId())
                .build();

    };










}
