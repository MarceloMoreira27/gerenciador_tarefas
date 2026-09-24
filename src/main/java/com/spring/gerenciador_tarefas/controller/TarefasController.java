package com.spring.gerenciador_tarefas.controller;


import com.spring.gerenciador_tarefas.business.TarefasService;
import com.spring.gerenciador_tarefas.business.dtos.TarefasDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService tarefasService;
    private TarefasDTO tarefasDTO;

    @PostMapping
    public ResponseEntity<TarefasDTO> salvaTarefas(@Valid @RequestBody TarefasDTO tarefasDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tarefasService.salvarTarefas(tarefasDTO));
    }


}
