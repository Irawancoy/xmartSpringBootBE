package com.backend.xmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.xmart.model.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, String>
{
   // find customer by qr code
   CustomerModel findByQrCode(String qrCode);


}
