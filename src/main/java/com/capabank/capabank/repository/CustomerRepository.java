package com.capabank.capabank.repository;

import com.capabank.capabank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {


}
