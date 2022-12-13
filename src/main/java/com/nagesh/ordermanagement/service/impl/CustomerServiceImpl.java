package com.nagesh.ordermanagement.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagesh.ordermanagement.Enums.CustomerTypeDiscountEnum;
import com.nagesh.ordermanagement.Vo.CustomerMasterVo;
import com.nagesh.ordermanagement.dao.CustomerRepository;
import com.nagesh.ordermanagement.entity.CustomerMaster;
import com.nagesh.ordermanagement.entity.CustomerTypeMaster;
import com.nagesh.ordermanagement.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Integer addCustomer(CustomerMasterVo customerMasterVo) {
		Integer customerId = null;
		try {
			if (customerMasterVo != null) {
				CustomerMaster customerMaster = new CustomerMaster();

				customerMaster.setCustomerEmail(customerMasterVo.getCustomerEmail());
				customerMaster.setCustomerId(customerMasterVo.getCustomerId());
				customerMaster.setCustomerName(customerMasterVo.getCustomerName());
				customerMaster.setCustomerPhone(customerMasterVo.getCustomerPhone());
				// This is to ensure customer is regular during creation

				CustomerTypeMaster customerTypeMaster = new CustomerTypeMaster();
				customerTypeMaster.setCustomerTypeId(CustomerTypeDiscountEnum.REGULAR.getId());

				customerMaster.setCustomerTypeMaster(customerTypeMaster);

				CustomerMaster savedEntity = customerRepository.save(customerMaster);
				customerId = savedEntity.getCustomerId();
			}
		} catch (Exception e) {
			System.out.println("exception");
		}

		return customerId;
	}

}
