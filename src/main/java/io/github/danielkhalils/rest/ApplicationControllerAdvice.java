package io.github.danielkhalils.rest;

import io.github.danielkhalils.rest.exceptions.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {


    //Intercepta sempre que houver uma excessão de erro
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErros(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map( objectError -> objectError.getDefaultMessage())
                .collect( Collectors.toList() );

        return new ApiErrors(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex){
        String mensageErrors = ex.getMessage();
        HttpStatus codigoStatus = (HttpStatus) ex.getStatusCode();
        ApiErrors apiErrors = new ApiErrors(mensageErrors);
        return new ResponseEntity(apiErrors, codigoStatus);
    }

}
