package com.nagesh.ordermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagesh.ordermanagement.entity.CustomerMaster;

public interface CustomerRepository extends JpaRepository<CustomerMaster, Integer> {

}
