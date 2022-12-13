package com.nagesh.ordermanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagesh.ordermanagement.Constants.CustomerConstants;
import com.nagesh.ordermanagement.Util.CommonResponse;
import com.nagesh.ordermanagement.Util.JsonUtil;
import com.nagesh.ordermanagement.Vo.CustomerMasterVo;
import com.nagesh.ordermanagement.service.CustomerService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = CustomerConstants.CUSTOMER_CLASS)
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping(value = CustomerConstants.ADD_CUSTOMER)
	public ResponseEntity<JSONObject> addCustomer(HttpServletRequest request, HttpServletResponse httpresponse)
			throws Exception {
		JSONObject respObject = null;
		Integer customerId;
		try {
			CustomerMasterVo masterVO = (CustomerMasterVo) JsonUtil.fromJson(request.getParameter("data"),
					CustomerMasterVo.class);
			if (masterVO != null && masterVO.getCustomerEmail() != null && masterVO.getCustomerName() != null) {
				customerId = customerService.addCustomer(masterVO);
				if (customerId != null && customerId > 0)
					respObject = CommonResponse.getSuccessResponse("success", "Customer saved successfully");
				respObject.put("customerId", customerId);
			} else {
				throw new Exception("Mandatory fields missing");
			}

		} catch (Exception e) {
			throw new Exception("Customer could not be added");

		}
		return new ResponseEntity<JSONObject>(respObject, HttpStatus.OK);

	}
}
