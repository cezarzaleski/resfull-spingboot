package com.cezarzaleski.RestApi.repository;

import com.cezarzaleski.RestApi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
