package com.nagesh.ordermanagement.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nagesh.ordermanagement.Enums.CustomerTypeDiscountEnum;
import com.nagesh.ordermanagement.Vo.OrderVo;
import com.nagesh.ordermanagement.dao.CustomerRepository;
import com.nagesh.ordermanagement.dao.OrderRepository;
import com.nagesh.ordermanagement.entity.CustomerMaster;
import com.nagesh.ordermanagement.entity.CustomerTypeMaster;
import com.nagesh.ordermanagement.entity.OrderMaster;
import com.nagesh.ordermanagement.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Integer addNewOrder(OrderVo orderVo) {
		Integer orderId = null;
		try {
			if (orderVo != null) {
				OrderMaster orderMaster = new OrderMaster();
				CustomerMaster customerMaster = new CustomerMaster();

				orderMaster.setTotalAmount(orderVo.getTotalAmount());
				orderMaster.setDiscountAmount(
						calculateDiscountBasedOnCustomerType(orderVo.getCustomerId(), orderVo.getTotalAmount()));
				customerMaster.setCustomerId(orderVo.getCustomerId());
				orderMaster.setCustomerMaster(customerMaster);

				OrderMaster savedEntity = orderRepository.save(orderMaster);
				orderId = savedEntity.getOrderId();

				if (orderId != null && orderId > 0) {
					// Check if user is eligible for promotion
					upgradeCustomerIfApplicable(orderVo.getCustomerId());
				}
			}
		} catch (Exception e) {
			System.out.println("exception");
		}

		return orderId;
	}

	@Override
	public double calculateDiscountBasedOnCustomerType(Integer customerId, Integer totalAmount) {
		try {
			if (customerId != null && customerId > 0) {
				Optional<CustomerMaster> customerMaster = customerRepository.findById(customerId);
				if (customerMaster.isPresent()) {
					CustomerMaster existingCustomerMaster = customerMaster.get();

					if (existingCustomerMaster.getCustomerTypeMaster()
							.getCustomerTypeId() == CustomerTypeDiscountEnum.REGULAR.getId()) {
						return 0;
					}
					if (existingCustomerMaster.getCustomerTypeMaster()
							.getCustomerTypeId() == CustomerTypeDiscountEnum.GOLD.getId()) {
						return totalAmount * (CustomerTypeDiscountEnum.GOLD.getDiscountValue());
					}
					if (existingCustomerMaster.getCustomerTypeMaster()
							.getCustomerTypeId() == CustomerTypeDiscountEnum.PLATINUM.getId()) {
						return totalAmount * (CustomerTypeDiscountEnum.PLATINUM.getDiscountValue());
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	@Async("threadPoolTaskExecutor")
	@Override
	public void upgradeCustomerIfApplicable(Integer customerId) {
		try {
			Integer customerOrderCount = 0;
			customerOrderCount = orderRepository.fetchCustomerOrderCount(customerId);
			Optional<CustomerMaster> customerMaster = customerRepository.findById(customerId);
			if (customerMaster.isPresent()) {
				CustomerMaster existingCustomerMaster = customerMaster.get();

				if (customerOrderCount != 0) {
					if (customerOrderCount > 0 && customerOrderCount < 10) {
						// Do nothing
					} else if (customerOrderCount >= 10 && customerOrderCount < 20) {
						CustomerTypeMaster customerTypeMaster = new CustomerTypeMaster();
						customerTypeMaster.setCustomerTypeId(CustomerTypeDiscountEnum.GOLD.getId());
						existingCustomerMaster.setCustomerTypeMaster(customerTypeMaster);
						customerRepository.save(existingCustomerMaster);
					} else if (customerOrderCount >= 20) {
						CustomerTypeMaster customerTypeMaster = new CustomerTypeMaster();
						customerTypeMaster.setCustomerTypeId(CustomerTypeDiscountEnum.PLATINUM.getId());
						existingCustomerMaster.setCustomerTypeMaster(customerTypeMaster);
						customerRepository.save(existingCustomerMaster);

					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
