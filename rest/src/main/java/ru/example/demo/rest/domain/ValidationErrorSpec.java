package ru.example.demo.rest.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder(toBuilder = true)
@Getter
@ToString
@Jacksonized
public class ValidationErrorSpec {
    private final String code;
    private final String message;
    private final Object rejectedValue;
}
