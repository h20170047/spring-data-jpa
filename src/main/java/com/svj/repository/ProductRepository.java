package com.svj.repository;

import com.svj.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    List<Product> findByProductType(String productType);

    List<Product> findByPriceAndProductType(double price, String type);

//    @Query(value = "SELECT * FROM PRODUCT_TBL WHERE PRICE=?1", nativeQuery = true)
    @Query("FROM Product p WHERE p.price= ?1") // position based parameter
//    @Query("FROM PRODUCT_TBL p WHERE p.price= :price")
//    List<Product> getProductByPrice(@Param() double price);
    List<Product> getProductByPrice( double price);
}
