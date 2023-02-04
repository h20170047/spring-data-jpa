package com.svj.service;

import com.svj.annotation.LogRequestAndResponse;
import com.svj.entity.Product;
import com.svj.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "products")
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository= productRepository;
    }

//    @PostConstruct
//    public void initDB(){
//        List<Product> products = IntStream.range(0, 10000)
//                .mapToObj(i -> new Product("product-" + i, new Random().nextInt(5000), "desc-" + i, "type-" + i))
//                .collect(Collectors.toList());
//        productRepository.saveAll(products);
//    }

    @CachePut(key = "#product.id")
    public Product saveProduct(Product product){
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Cacheable(cacheNames = "products")
    public List<Product> getProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Cacheable(key = "#id")
    @LogRequestAndResponse
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

    @CachePut(key = "#id")
    @LogRequestAndResponse
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

    @CacheEvict(key = "#id")
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    // Operator

    public List<Product> getProductsByMultiplePriceValues(List<Double> prices){
        return productRepository.findByPriceIn(prices);
    }

    public List<Product> getProductsBetweenPriceRange(Double minPrice, Double maxPrice){
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductWithHigherPrice(double price){
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> getProductWithLowerPrice(double price){
        return productRepository.findByPriceLessThan(price);
    }


    public List<Product> getProductsWithLike(String name){
        return productRepository.findByNameIgnoreCaseContaining(name);
    }

    // sorting
    public List<Product> getProductsWithSorting(String fieldName){
        return productRepository.findAll( Sort.by(Sort.Direction.ASC, fieldName));
    }

    // pagination
    public Page<Product> getProductsWithPageResponse(int offSet, int limit){
        return productRepository.findAll(PageRequest.of(offSet, limit));
    }

    public List<Product> getProductsWithSortingAndPagination(String fieldName, int offset, int limit){
        return productRepository
                .findAll(
                        PageRequest.of(offset, limit)
                                .withSort(Sort.by(fieldName))
                ).getContent();
    }
}
