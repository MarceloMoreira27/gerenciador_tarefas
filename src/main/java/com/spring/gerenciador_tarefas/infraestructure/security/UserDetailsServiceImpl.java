package com.spring.gerenciador_tarefas.infraestructure.security;


import com.spring.gerenciador_tarefas.business.dtos.UsuarioDTO;
import com.spring.gerenciador_tarefas.infraestructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl  {

    // Repositório para acessar dados de usuário no banco de dados


    private final UsuarioClient client;

    public UserDetails carregaDadosUsuario(String email,String token) {

        UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email, token);
        return User.withUsername(usuarioDTO.getEmail())
                    .password(usuarioDTO.getSenha())
                    .build();
    }
}
