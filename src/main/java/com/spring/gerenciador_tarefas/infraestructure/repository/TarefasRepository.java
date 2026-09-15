package com.spring.gerenciador_tarefas.infraestructure.repository;

import com.spring.gerenciador_tarefas.infraestructure.entity.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefasRepository extends JpaRepository<Tarefas, Long> {
}
