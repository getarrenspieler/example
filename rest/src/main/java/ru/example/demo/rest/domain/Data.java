package ru.example.demo.rest.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@Schema(description = "Данные")
@Jacksonized
@ToString
public class Data {

    @Schema(description = "Идентификатор продавца", required = true)
    @NotBlank(message = "Customer should be not null and not empty")
    @Size(min = 9, max = 9, message = "Customer size {max} is expected (${validatedValue}).")
    final String customer;

    @Schema(description = "Идентификатор продавца", required = true)
    @NotBlank(message = "Seller should be not null and not empty")
    @Size(min = 9, max = 9, message = "Seller size {max} is expected (${validatedValue}).")
    final String seller;

    @Schema(description = "Продукты", required = true)
    @NotEmpty(message = "Products should not be empty")
    final Set<@Valid Product> products;
}
