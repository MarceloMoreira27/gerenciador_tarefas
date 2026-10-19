package com.spring.gerenciador_tarefas.infraestructure.repository;

import com.spring.gerenciador_tarefas.infraestructure.entity.TarefasEntity;
import com.spring.gerenciador_tarefas.infraestructure.enuns.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefasRepository extends JpaRepository<TarefasEntity, Long> {


    List<TarefasEntity> findByUsuarioId(Long usuarioId);

    List<TarefasEntity> findByUsuarioIdAndStatus(Long usuarioId, StatusEnum status);

}
