package com.svj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svj.controller.ProductController;
import com.svj.entity.Product;
import com.svj.repository.ProductRepository;
import com.svj.service.ProductService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // whole spring context will be loaded
@AutoConfigureMockMvc // tells springboot to do necessary web layer autoconfigs
@ExtendWith(MockitoExtension.class) // mocks necessary dependencies using mocks to the field using @InjectMocks
class SpringDataJpaApplicationTests {

	private final String ENDPOINT_URL="/products";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@InjectMocks
	private ProductController prouductController;

	@BeforeEach
	public void setUp(){
		this.mockMvc= MockMvcBuilders.standaloneSetup(prouductController).build();
	}

	@Test
	public void addProductTest() throws Exception {
		Product product=Product.builder()
				.name("mobile")
				.description("samsung latest")
				.productType("Electronics")
				.build();
		Product savedProduct= product; savedProduct.setId(1);
		when(productService.saveProduct(product)).thenReturn(savedProduct);
		mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
				.content(new ObjectMapper().writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists());
	}

}
