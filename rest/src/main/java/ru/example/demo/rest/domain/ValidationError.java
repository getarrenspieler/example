package ru.example.demo.rest.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Map;

/**
 * Структура для отправки ошибок валидации на фронт.
 */
@Jacksonized
@Builder
@Getter
@ToString
public class ValidationError {

    /**
     * Ошибки валидации полей.
     * <p>
     * Ключ     - поле, которое не прошло проверку<br/>
     * значение - список ошибок
     * </p>
     */
    private final Map<String, List<ValidationErrorSpec>> fields;

}
