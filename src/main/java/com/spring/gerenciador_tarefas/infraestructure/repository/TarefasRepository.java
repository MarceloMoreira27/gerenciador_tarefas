package com.spring.gerenciador_tarefas.infraestructure.repository;

import com.spring.gerenciador_tarefas.infraestructure.entity.TarefasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefasRepository extends JpaRepository<TarefasEntity, Long> {
}
