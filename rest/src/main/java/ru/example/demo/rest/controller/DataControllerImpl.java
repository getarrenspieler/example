package ru.example.demo.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.example.demo.rest.domain.Data;
import ru.example.demo.rest.domain.ValidationError;
import ru.example.demo.rest.domain.ValidationErrorSpec;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер.
 */
@RestController
@RequestMapping("/data")
@Slf4j
public class DataControllerImpl implements DataController {

    @PostMapping(value = "/process", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<String> processData(@Valid @RequestBody Data data) {
        log.debug("Processed: {}", data);
        return ResponseEntity.ok("");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Validation error:", ex);
        final List<FieldError> fieldErrors = ex.getFieldErrors();

        return ValidationError.builder()
                .fields(fieldErrors.stream()
                        .collect(groupingBy(FieldError::getField,
                                collectingAndThen(toList(), x ->
                                        x.stream()
                                                .map(fe -> ValidationErrorSpec.builder()
                                                        .rejectedValue(fe.getRejectedValue())
                                                        .code(fe.getCode())
                                                        .message(fe.getDefaultMessage())
                                                        .build())
                                                .collect(toList()))
                        )))
                .build();
    }
}
