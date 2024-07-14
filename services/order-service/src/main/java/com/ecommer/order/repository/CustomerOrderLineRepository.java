package com.ecommer.order.repository;

import com.ecommer.order.model.entity.CustomerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderLineRepository extends JpaRepository<CustomerOrderLine, Integer> {
    List<CustomerOrderLine> findAllByCustomerOrderId(Integer orderId);
}
