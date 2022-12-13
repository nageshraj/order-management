package com.nagesh.ordermanagement.service;

import com.nagesh.ordermanagement.Vo.OrderVo;

public interface OrderService {

	Integer addNewOrder(OrderVo orderVo);

	double calculateDiscountBasedOnCustomerType(Integer customerId, Integer totalAmount);

	void upgradeCustomerIfApplicable(Integer customerId);

}
