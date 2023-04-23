package com.cm.tdbWebhook.repo;


import com.cm.tdbWebhook.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}