package com.spring.gerenciador_tarefas.business;


import com.spring.gerenciador_tarefas.business.dtos.TarefasDTO;
import com.spring.gerenciador_tarefas.business.mapper.TarefasMapper;
import com.spring.gerenciador_tarefas.infraestructure.entity.Tarefas;
import com.spring.gerenciador_tarefas.infraestructure.enuns.StatusEnum;
import com.spring.gerenciador_tarefas.infraestructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasMapper mapper;

    public TarefasDTO salvarTarefas(TarefasDTO dto){
        Tarefas entity = mapper.paraTarefas(dto);
        entity.setStatus(StatusEnum.PENDENTE);
        entity.setDataCriacao(LocalDateTime.now());

        entity.setId(1L);
        return mapper.paraTarefasDTO(tarefasRepository.save(entity));
    }



}
