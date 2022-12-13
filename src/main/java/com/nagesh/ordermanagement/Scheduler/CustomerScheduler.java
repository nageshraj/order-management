package com.nagesh.ordermanagement.Scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.nagesh.ordermanagement.dao.OrderRepository;

public class CustomerScheduler {

	@Autowired
	OrderRepository orderRepository;

	// This runs the scheduler once every day
	@Scheduled(fixedDelay = 86400000)
	public void runCustomerUpgradeEmailActivity() {
		StringBuilder emailContent = new StringBuilder();
		List<Object[]> customerOrderCountList = orderRepository.findAllCustomersOrderCount();
		for (Object[] eachCustomer : customerOrderCountList) {
			if (Integer.valueOf(eachCustomer[0].toString()) > 20) {
				// Customer is already platinum
				break;
			}
			if (Integer.valueOf(eachCustomer[0].toString()) > 10) {
				// Customer is Gold so send mail
				Integer orderCount = Integer.valueOf(eachCustomer[0].toString());
				emailContent.append("You have placed " + orderCount + " orders with us. Kindly place "
						+ (20 - orderCount) + " to be upgraded to PLATINUM and enjoy 20% discount");
			}
			if (Integer.valueOf(eachCustomer[0].toString()) > 0) {
				// Customer is regular so send mail
				Integer orderCount = Integer.valueOf(eachCustomer[0].toString());
				emailContent.append("You have placed " + orderCount + " orders with us. Kindly place "
						+ (10 - orderCount) + " to be upgraded to GOLD and enjoy 10% discount");
			}

			sendMail(emailContent.toString(), eachCustomer[1].toString());
		}

	}

	void sendMail(String emailContent, String emailRecepient) {
		System.out.println("Email Content: " + emailContent);
		System.out.println("Email Recepient: " + emailRecepient);

	}

}
