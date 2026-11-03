package mz.co.escola.escolaapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarValidacao(
            MethodArgumentNotValidException ex) {

        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "erro", "Dados inválidos",
                        "mensagens", erros,
                        "data", LocalDateTime.now().toString()
                )
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> tratarNaoEncontrado(
            ResponseStatusException ex) {

        int status = ex.getStatusCode().value();

        return ResponseEntity.status(status).body(
                Map.of(
                        "status", status,
                        "erro", "Erro na operação",
                        "mensagem", ex.getReason() == null
                                ? "Ocorreu um erro"
                                : ex.getReason(),
                        "data", LocalDateTime.now().toString()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> tratarErroGeral(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Map.of(
                                "status", 500,
                                "erro", "Erro interno do servidor",
                                "mensagem", "Ocorreu um erro inesperado",
                                "data", LocalDateTime.now().toString()
                        )
                );
    }
}