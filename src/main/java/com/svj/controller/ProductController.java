package com.svj.controller;

import com.svj.entity.Product;
import com.svj.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService= productService;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
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
}
