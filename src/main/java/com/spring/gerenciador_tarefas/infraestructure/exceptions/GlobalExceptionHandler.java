package com.spring.gerenciador_tarefas.infraestructure.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Recurso Não Encontrado (404) -> ex: Tarefa não existe no banco
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problemDetail.setTitle("Recurso Não Encontrado");
        problemDetail.setType(URI.create("https://api.gerenciadortarefas.com/erros/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("path", request.getRequestURI());

        return problemDetail;
    }

    // 2. Conflito / Regra de Negócio Violada (409) -> ex: Alterar tarefa CONCLUÍDA
    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflictException(ConflictException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );

        problemDetail.setTitle("Conflito de Regra de Negócio");
        problemDetail.setType(URI.create("https://api.gerenciadortarefas.com/erros/conflict"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("path", request.getRequestURI());

        return problemDetail;
    }

    // 3. Erro de Validação de DTOs / @Valid (400) -> ex: Campos nulos ou vazios no Body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Um ou mais campos inseridos são inválidos."
        );
        problemDetail.setTitle("Erro de Validação de Entrada");
        problemDetail.setType(URI.create("https://api.gerenciadortarefas.com/erros/bad-request"));

        Map<String, String> invalidFields = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                invalidFields.put(error.getField(), error.getDefaultMessage())
        );

        problemDetail.setProperty("invalidFields", invalidFields);
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("path", request.getRequestURI());

        return problemDetail;
    }

    // 4. Erro de Tipo/Enum na URL (400) -> ex: GET /tarefas?status=PENDENT
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String detail = String.format("O parâmetro '%s' recebeu o valor '%s', que é inválido.", ex.getName(), ex.getValue());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                detail
        );
        problemDetail.setTitle("Parâmetro de URL Inválido");
        problemDetail.setType(URI.create("https://api.gerenciadortarefas.com/erros/invalid-param"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("path", request.getRequestURI());

        return problemDetail;
    }



    // 5. Erro de JSON Malformado ou Enum Inválido no Body (400) -> ex: PATCH com JSON quebrado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "O corpo da requisição (JSON) é inválido ou contém valores incompatíveis."
        );
        problemDetail.setTitle("Corpo da Requisição Inválido");
        problemDetail.setType(URI.create("https://api.gerenciadortarefas.com/erros/invalid-body"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("path", request.getRequestURI());

        return problemDetail;
    }

    // 6. Catch-All para erros não previstos (500) -> Impede vazar exceções cruas do sistema
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUncaughtException(Exception ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno inesperado nos nossos servidores."
        );
        problemDetail.setTitle("Erro Interno do Servidor");
        problemDetail.setType(URI.create("https://api.gerenciadortarefas.com/erros/internal-server-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("path", request.getRequestURI());

        return problemDetail;
    }


    // Intercepta falhas de validação colocadas diretamente na assinatura do método da Controller.
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Um ou mais parâmetros enviados na URL são inválidos."
        );
        problemDetail.setTitle("Parâmetro Inválido");
        problemDetail.setType(URI.create("https://api.gerenciadortarefas.com/erros/invalid-param"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("path", request.getRequestURI());

        return problemDetail;
    }

    //Captura erros lançados quando o código tenta processar um argumento inadequado ou incompatível no Java.
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        ex.printStackTrace(); // <---  linha para ver a pilha inteira no terminal!
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        problemDetail.setTitle("Requisição Inválida");
        problemDetail.setType(URI.create("https://api.gerenciadortarefas.com/erros/bad-request"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("path", request.getRequestURI());

        return problemDetail;
    }


}