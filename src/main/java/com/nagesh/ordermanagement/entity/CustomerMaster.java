package com.nagesh.ordermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customer_master")
public class CustomerMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "customer_email")
	private String customerEmail;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_phone")
	private String customerPhone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_type_id", nullable = false, referencedColumnName = "customer_type_id")
	private CustomerTypeMaster customerTypeMaster;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public CustomerTypeMaster getCustomerTypeMaster() {
		return customerTypeMaster;
	}

	public void setCustomerTypeMaster(CustomerTypeMaster customerTypeMaster) {
		this.customerTypeMaster = customerTypeMaster;
	}

}
