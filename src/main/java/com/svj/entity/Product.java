package com.svj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_TBL")
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;
    private String productType;

    @CreatedBy
    private String createdBy;
    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;
}
