package com.svj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svj.entity.Product;
import com.svj.entity.RequestObj;
import com.svj.exception.InvalidInput;
import com.svj.service.ProductService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private ObjectMapper objectMapper;
    public ProductController(ProductService productService){
        this.productService= productService;
        objectMapper= new ObjectMapper();
    }

    @PostMapping
    public Product saveProduct(@RequestBody RequestObj product){
        Product payload = objectMapper.convertValue(product.getPayload(), Product.class) ;
        if(payload.getPrice()< 0)
            throw new InvalidInput("Product price should not be negative");
        return productService.saveProduct(payload);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @GetMapping("/name/{name}")
    public Product getProductName(@PathVariable String name){
        return productService.getProductName(name);
    }


    @GetMapping("/type/{productType}")
    public List<Product> getProductsByType(@PathVariable String productType){
        return productService.getProductsByType(productType);
    }

    @GetMapping("/type/{type}/price/{price}")
    public List<Product> getProductByPriceAndType(@PathVariable String type, @PathVariable double price){
        return productService.getProductByPriceAndType(type, price);
    }

    @GetMapping("/price/{price}")
    public List<Product> getProductByPrice(@PathVariable double price){
        return productService.getProductByPrice(price);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest){
        return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }

    @PostMapping("/price/search")
    public List<Product> getProductsByMultiplePriceValues(@RequestBody List<Double> prices){
        return productService.getProductsByMultiplePriceValues(prices);
    }

    @GetMapping("price/min/{minPrice}/max/{maxPrice}")
    public List<Product> getProductsBetweenPriceRange(@PathVariable Double minPrice, @PathVariable Double maxPrice){
        return productService.getProductsBetweenPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/price/min/{price}")
    public List<Product> getProductWithHigherPrice(@PathVariable double price){
        return productService.getProductWithHigherPrice(price);
    }

    @GetMapping("/price/max/{price}")
    public List<Product> getProductWithLowerPrice(@PathVariable Double price){
        return productService.getProductWithLowerPrice(price);
    }

    @GetMapping("/name/like/{name}")
    public List<Product> getProductsWithLike(@PathVariable String name){
        return productService.getProductsWithLike(name);
    }

    @GetMapping("/sort/name/{fieldName}")
    public List<Product> getProductsWithSorting(@PathVariable String fieldName){
        return productService.getProductsWithSorting( fieldName);
    }

    @GetMapping("/page/offSet/{offSet}/limit/{limit}")
    public Page<Product> getProductsWithPageResponse(@PathVariable Integer offSet, @PathVariable Integer limit){
        return productService.getProductsWithPageResponse(offSet, limit);
    }

    @GetMapping("/pageAndSort/name/{fieldName}/offSet/{offSet}/limit/{limit}")
    public List<Product> getProductsWithSortingAndPagination(@PathVariable String fieldName, @PathVariable Integer offSet, @PathVariable Integer limit){
        return productService.getProductsWithSortingAndPagination(fieldName, offSet, limit);
    }
}
