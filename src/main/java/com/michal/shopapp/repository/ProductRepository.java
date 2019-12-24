package com.michal.shopapp.repository;

import com.michal.shopapp.components.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
