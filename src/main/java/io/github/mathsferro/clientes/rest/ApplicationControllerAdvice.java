package io.github.mathsferro.clientes.rest;

import io.github.mathsferro.clientes.rest.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    // Exception de Validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Retorno BAD_REQUEST para o Http
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult(); // Objeto que tem todos o resultado da validação
        // objectError.getDefaultMessage vai retornar a mensagem de erro da variável
        List<String> collect = bindingResult.getAllErrors()
                .stream()
                .map( objectError -> objectError.getDefaultMessage() ) // Array de Errors e qual campo ocorreu o erro
                .collect(Collectors.toList()); // Collect pega tudo que ta na string e coloca na forma de lista
        return new ApiErrors(collect); // Toda vez que tiver um erro de validação vai executar o método
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
        String messagemError = ex.getMessage();
        HttpStatus codigoStatus = ex.getStatus();
        ApiErrors apiErrors = new ApiErrors(messagemError);
        return new ResponseEntity(apiErrors, codigoStatus);
    }

}
