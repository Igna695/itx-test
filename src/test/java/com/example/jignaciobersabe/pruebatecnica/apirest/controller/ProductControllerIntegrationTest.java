package com.example.jignaciobersabe.pruebatecnica.apirest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@EmbeddedKafka(brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=9092"})
@SpringBootTest
class ProductControllerIntegrationTest {
    @Autowired
    private ProductController productController;

    private MockMvc mockMvc;

    private static String PATH = "/api/v1/getProducts";
    private static String PRODUCT_ID = "35455";
    private static String BRAND_ID = "1";

    private String wrongDate = "2021-06-20T16:00:00Z";

    public static final long EXPECTED_PRODUCT_ID = 35455L;
    public static final long EXPECTED_BRAND_ID = 1L;
    public static final Double EXPECTED_PRICE = 38.95;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void given_test_data_when_call_productoController_then_return_ok_and_correct_data() throws Exception {
        List<String> dateList = Arrays.asList(
                "2020-06-20T16:00:00Z", "2020-06-15T16:00:00Z", "2020-06-15T18:00:00Z", "2020-06-20T16:00:00Z");

        dateList.forEach(o -> {
            try {
                okResponse(o);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void given_wrong_test_data_when_call_productoController_then_return_bad_request() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .param("date", wrongDate)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private void okResponse(String date) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .param("date", date)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.productId").value(EXPECTED_PRODUCT_ID))
                .andExpect(jsonPath("$.brandId").value(EXPECTED_BRAND_ID))
                .andExpect(jsonPath("$.price").value(EXPECTED_PRICE));
    }
}
