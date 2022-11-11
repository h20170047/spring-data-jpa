package com.svj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svj.controller.ProductController;
import com.svj.entity.Product;
import com.svj.repository.ProductRepository;
import com.svj.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // whole spring context will be loaded
@AutoConfigureMockMvc // tells springboot to do necessary web layer autoconfigs
@ExtendWith(MockitoExtension.class) // mocks necessary dependencies using mocks to the field using @InjectMocks
class SpringDataJpaApplicationTests {

	private final String ENDPOINT_URL="/products";

	ObjectMapper objectMapper= new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductController productController;

	@BeforeEach
	public void setUp(){
		this.mockMvc= MockMvcBuilders
				.standaloneSetup(productController)
				.build();
	}

	@Test
	public void addProductTest() throws Exception {
		Product product=Product.builder()
				.name("mobile")
				.description("samsung latest")
				.productType("Electronics")
				.build();
		Product savedProduct= product; savedProduct.setId(1);
		when(productRepository.save(any())).thenReturn(savedProduct);
		mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
				.content(writeJson(product))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists());
	}

	private String writeJson(Product product) throws JsonProcessingException {
		return objectMapper.writeValueAsString(product);
	}

	@Test
	public void getProductsShouldReturnAllProducts() throws Exception {
		Product product1= new Product(1, "demo1", 1000, "Demo desc", "demo", "demo", null, null, null);
		Product product2= new Product(2, "demo2", 2000, "Demo desc", "demo", "demo", null, null, null);
		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*").exists())
				.andExpect(jsonPath("$.[0].id").value(1));
	}

	@Test
	public void getProductsByIDShouldReturnCorrespondingProduct() throws Exception {
		Product product= new Product(1, "demo1", 1000, "Demo desc", "demo", "demo", null, null, null);
		when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL+"/{id}", 1)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*").exists())
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	public void updateProductShouldPersist() throws Exception {
		Product product= new Product(1, "demo1", 1000, "Demo desc", "demo", "demo", null, null, null);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		when(productRepository.findById(any())).thenReturn(Optional.of(product));
		mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_URL+"/{id}", 1)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(product))
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description").value("Demo desc"))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	public void deleteProductShouldRemoveItem() throws Exception {
		doNothing().when(productRepository).deleteById(anyInt());
		mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL+"/{id}", 1)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
