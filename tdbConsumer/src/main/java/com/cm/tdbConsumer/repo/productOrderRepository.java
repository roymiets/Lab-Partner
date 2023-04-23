package com.cm.tdbConsumer.repo;

import com.cm.tdbConsumer.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productOrderRepository extends JpaRepository<ProductOrder, Long> {
}