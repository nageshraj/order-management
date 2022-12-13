package com.nagesh.ordermanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagesh.ordermanagement.Constants.OrderConstants;
import com.nagesh.ordermanagement.Util.CommonResponse;
import com.nagesh.ordermanagement.Util.JsonUtil;
import com.nagesh.ordermanagement.Vo.OrderVo;
import com.nagesh.ordermanagement.service.OrderService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = OrderConstants.ORDER_CLASS)
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping(value = OrderConstants.NEW_ORDER)
	public ResponseEntity<JSONObject> addNewOrder(HttpServletRequest request, HttpServletResponse httpresponse)
			throws Exception {
		JSONObject respObject = null;
		Integer orderId;
		try {
			OrderVo masterVO = (OrderVo) JsonUtil.fromJson(request.getParameter("data"), OrderVo.class);
			if (masterVO != null && masterVO.getCustomerId() != null && masterVO.getTotalAmount() != null) {
				orderId = orderService.addNewOrder(masterVO);
				if (orderId != null && orderId > 0)
					respObject = CommonResponse.getSuccessResponse("success", "Order created successfully");
			} else {
				throw new Exception("Mandatory fields missing");
			}

		} catch (Exception e) {
			throw new Exception("Order could not be added");

		}
		return new ResponseEntity<JSONObject>(respObject, HttpStatus.OK);

	}

}
