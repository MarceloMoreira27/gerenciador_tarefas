package com.spring.gerenciador_tarefas.business.mapper;

import com.spring.gerenciador_tarefas.business.dtos.TarefasDTO;
import com.spring.gerenciador_tarefas.infraestructure.entity.TarefasEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TarefasMapper {

    public TarefasEntity paraTarefasEntity(TarefasDTO dto){
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

    public List<TarefasDTO>paraListaTarefasDTO(List<TarefasEntity>entitys){
        return entitys.stream().map(this::paraTarefasDTO).toList();
    }

    public List<TarefasEntity>paraListaTarefasEntity(List<TarefasDTO>tarefasDTOS){

        List<TarefasEntity> listaEntity = new ArrayList<>();
        for (TarefasDTO tarefasDTO : tarefasDTOS){
            listaEntity.add(paraTarefasEntity(tarefasDTO));
        }

        return listaEntity;

    }

    public TarefasEntity updateTarefas(TarefasEntity entity,TarefasDTO dto){
        return TarefasEntity.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .status(entity.getStatus())
                .prioridade(dto.getPrioridade())
                .dataLimite(dto.getDataLimite())
                .dataCriacao(entity.getDataCriacao())
                .dataConclusao(entity.getDataConclusao())
                .usuarioId(entity.getUsuarioId())
                .id(entity.getId())
                .build();
    }











}
