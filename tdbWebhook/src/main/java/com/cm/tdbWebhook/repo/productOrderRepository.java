package com.cm.tdbWebhook.repo;

import com.cm.tdbWebhook.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productOrderRepository extends JpaRepository<ProductOrder, Long> {
}