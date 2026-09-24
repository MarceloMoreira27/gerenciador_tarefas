package com.spring.gerenciador_tarefas.infraestructure.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SecurityUtils {

    public static String getUsuarioLogado() {
        // 1. Obtém a autenticação do contexto da thread atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2. Valida se a autenticação existe e está ativa
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            // Se o principal for um UserDetails (padrão do Spring Security)
            if (principal instanceof UserDetails userDetails) {
                return userDetails.getUsername(); // Retorna o e-mail/username
            }

            // Caso o principal seja apenas uma String (ex: Subject do JWT)
            if (principal instanceof String username) {
                return username;
            }
        }

        throw new IllegalStateException("Nenhum usuário autenticado no contexto de segurança.");
    }

    /**
     * Extrai o token JWT da requisição HTTP atual.
     * Segue o padrão de limpar o prefixo "Bearer ", devolvendo apenas a String do token.
     */
    public static String getToken() {
        // Captura os atributos da requisição associada à Thread atual
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            return null;
        }

        // Obtém o HttpServletRequest da requisição Web ativa
        HttpServletRequest request = attributes.getRequest();
        String bearerToken = request.getHeader("Authorization");

        // Valida se o cabeçalho existe e possui o formato padrão do protocolo Bearer
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken; // Com a palavra "Bearer " e os espaços
        }

        return null;
    }
}