package com.spring.gerenciador_tarefas.business;


import com.spring.gerenciador_tarefas.business.dtos.TarefasDTO;
import com.spring.gerenciador_tarefas.business.dtos.UsuarioDTO;
import com.spring.gerenciador_tarefas.business.mapper.TarefasMapper;
import com.spring.gerenciador_tarefas.infraestructure.client.UsuarioClient;
import com.spring.gerenciador_tarefas.infraestructure.entity.TarefasEntity;
import com.spring.gerenciador_tarefas.infraestructure.enuns.StatusEnum;
import com.spring.gerenciador_tarefas.infraestructure.exceptions.ConflictException;
import com.spring.gerenciador_tarefas.infraestructure.exceptions.ResourceNotFoundException;
import com.spring.gerenciador_tarefas.infraestructure.repository.TarefasRepository;
import com.spring.gerenciador_tarefas.infraestructure.security.SecurityUtils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasMapper mapper;
    private final UsuarioClient client;


    public TarefasDTO salvarTarefas(TarefasDTO dto){

        TarefasEntity entity = mapper.paraTarefasEntity(dto);
        entity.setStatus(StatusEnum.PENDENTE);
        entity.setDataCriacao(LocalDateTime.now());
        entity.setUsuarioId(buscarIdUsuario());

        return mapper.paraTarefasDTO(tarefasRepository.save(entity));
    }

    public Long buscarIdUsuario(){
        try{

            String email = SecurityUtils.getUsuarioLogado();
            String token = SecurityUtils.getToken();

            UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email,token);

            if (usuarioDTO == null || usuarioDTO.getId() == null) {
                throw new ResourceNotFoundException("Usuário não encontrado");
            }

            return usuarioDTO.getId();

        } catch (FeignException e) {
            throw new ResourceNotFoundException("Erro ao localizar id do usuario " + e.getMessage());
        }

    }

    public List<TarefasDTO>buscarTarefasOpcionalStatus(String status){
        Long id = buscarIdUsuario();
        List<TarefasEntity> entitys;


        if (status == null || status.isBlank()) {
              entitys = tarefasRepository.findByUsuarioId(id);

          }else {
            StatusEnum statusEnum = StatusEnum.valueOf(status);
            entitys = tarefasRepository.findByUsuarioIdAndStatus(id, statusEnum);
          }

          return mapper.paraListaTarefasDTO(entitys);

    }


    @Transactional(readOnly = true)
    public TarefasDTO buscarTarefasPorID(Long id){
        return mapper.paraTarefasDTO(tarefasRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Id da tarefa não localizado, id: "+id)));
    }

    private void verificaStatusTarefaConcluida(StatusEnum status){
        if (StatusEnum.CONCLUIDA.equals(status)){
            throw new ConflictException("Tarefa CONCLUÍDA não pode ser alterada!");
        }
    }



    @Transactional
    public TarefasDTO atualizarTarefasPorId(Long id,TarefasDTO tarefasDTO){

        TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Id da tarefa não localizado, id: "+id));

        verificaStatusTarefaConcluida(entity.getStatus());

        TarefasEntity novaEntity = mapper.updateTarefas(entity,tarefasDTO);
        return mapper.paraTarefasDTO(tarefasRepository.save(novaEntity));

    }






}
