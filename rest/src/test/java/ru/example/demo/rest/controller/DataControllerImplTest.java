package ru.example.demo.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.example.demo.rest.domain.Data;
import ru.example.demo.rest.domain.Product;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DataControllerImplTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void handleMethodArgumentNotValidException() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Data data = Data.builder()
                .seller("123534")
                .customer(null)
                .products(Set.of(
                        Product.builder().name("milk").code("").build(),
                        Product.builder().name(null).code("6352437590").build()
                ))
                .build();
        String request = objectMapper.writeValueAsString(data);

        String response = mockMvc.perform(post("/data/process")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertThat(response)
                .containsIgnoringWhitespaces("Seller size 9 is expected (123534)")
                .containsIgnoringWhitespaces("Product name should be not null and not empty")
                .containsIgnoringWhitespaces("Product code size 13 is expected ()")
                .containsIgnoringWhitespaces("Product code size 13 is expected (6352437590)")
                .containsIgnoringWhitespaces("Product code should be not null and not empty")
                .containsIgnoringWhitespaces("Customer should be not null and not empty");


    }
}