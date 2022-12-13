package com.nagesh.ordermanagement.Enums;

public enum CustomerTypeDiscountEnum {

	REGULAR((short) 1, "Regular", 0.0), GOLD((short) 2, "Gold", 0.1), PLATINUM((short) 3, "Platinum", 0.2);

	private final short id;
	private final String customerType;
	private final double discountValue;

	private CustomerTypeDiscountEnum(final short id, final String customerType, final double discountValue) {
		this.id = id;
		this.customerType = customerType;
		this.discountValue = discountValue;

	}

	public short getId() {
		return id;
	}

	public String getCustomerType() {
		return customerType;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	@Override
	public String toString() {
		return getCustomerType();
	}

}
