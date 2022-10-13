package com.svj.repository;

import com.svj.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface ProductRepository extends RevisionRepository<Product, Integer, Integer>,
        JpaRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findByProductType(String productType);

    List<Product> findByPriceAndProductType(double price, String type);

//    @Query(value = "SELECT * FROM PRODUCT_TBL WHERE PRICE=?1", nativeQuery = true)
    @Query("FROM Product p WHERE p.price= ?1") // position based parameter
//    @Query("FROM PRODUCT_TBL p WHERE p.price= :price")
//    List<Product> getProductByPrice(@Param() double price);
    List<Product> getProductByPrice( double price);

    // prefix + field + operator
    List<Product> findByPriceIn(List<Double> prices);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByNameIgnoreCaseContaining(String name);
}
