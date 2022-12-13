package com.nagesh.ordermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagesh.ordermanagement.entity.OrderMaster;

public interface OrderRepository extends JpaRepository<OrderMaster, Integer> {

	@Query(value = "select count(order_id) from order_master where customer_id = :customerId", nativeQuery = true)
	Integer fetchCustomerOrderCount(@Param("customerId") Integer customerId);

	@Query(value = "SELECT COUNT(om.order_id),cm.customer_email FROM order_master om inner join customer_master cm on om.customer_id = cm.customer_id  group by om.customer_id", nativeQuery = true)
	List<Object[]> findAllCustomersOrderCount();

}
