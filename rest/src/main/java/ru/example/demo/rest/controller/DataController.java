package ru.example.demo.rest.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.example.demo.rest.domain.Data;

/**
 * Контроллер для работы с сущностью Контрагент ui.
 */
@Tag(name = "DataController", description = "Контроллер для работы с данными ")
public interface DataController {

    @Operation(summary = "Обработать данные")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.HTTP_OK, description = "Successfully"),
            @ApiResponse(responseCode = Constants.HTTP_ERROR, description = "Failed"),
            @ApiResponse(responseCode = Constants.BAD_REQUEST, description = "Bad request")})
    ResponseEntity<String> processData(@Parameter(description = "Данные", required = true) Data data);
}
