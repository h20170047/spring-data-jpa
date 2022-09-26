package com.svj.service;

import com.svj.entity.Product;
import com.svj.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository= productRepository;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(int id){
        return productRepository.findById(id).get();
    }

    public Product getProductName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> getProductsByType(String productType){
        return productRepository.findByProductType(productType);
    }

    public List<Product> getProductByPriceAndType(String type, double price){
        return productRepository.findByPriceAndProductType(price, type);
    }

    public List<Product> getProductByPrice(double price){
        return productRepository.getProductByPrice(price);
    }

    public Product updateProduct(int id, Product productRequest){
        // get product from db using id
        // update with new value getting from request
        Product existingProduct= productRepository.findById(id).get();
        existingProduct.setName(productRequest.getName());
        existingProduct.setProductType(productRequest.getProductType());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}
