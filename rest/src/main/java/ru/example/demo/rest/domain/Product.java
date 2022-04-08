package ru.example.demo.rest.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder(toBuilder = true)
@EqualsAndHashCode
@Getter
@Schema(description = "Продукт")
@Jacksonized
@ToString
public class Product {

    @Schema(description = "Наименование", required = true)
    @NotBlank(message = "Product name should be not null and not empty")
    final String name;

    @Schema(description = "Код", required = true)
    @NotBlank(message = "Product code should be not null and not empty")
    @Size(min = 13, max = 13, message = "Product code size {max} is expected (${validatedValue})")
    final String code;
}
