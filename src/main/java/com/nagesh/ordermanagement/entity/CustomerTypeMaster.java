package com.nagesh.ordermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customer_type_master")
public class CustomerTypeMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "customer_type_id")
	private short customerTypeId;

	@Column(name = "customer_type_name")
	private String customerTypeName;

	public short getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(short customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

}
