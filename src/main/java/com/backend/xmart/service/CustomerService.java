package com.backend.xmart.service;

import org.springframework.stereotype.Service;

import com.backend.xmart.dto.response.ResponseBody;
import com.backend.xmart.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.backend.xmart.dto.response.CustomerResponse;
import org.springframework.data.domain.Pageable;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import com.backend.xmart.model.CustomerModel;

import org.springframework.http.HttpStatus;

@Service
public class CustomerService {

   @Autowired
   private CustomerRepository customerRepository;
   public ResponseEntity<ResponseBody> getAllCustomers(Pageable pageable) {
      ResponseBody responseBody = new ResponseBody();
      try {
         Page<CustomerModel> customers = customerRepository.findAll(pageable);
         List<CustomerResponse> response = customers.stream().map(customer -> {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setQrCode(customer.getQrCode());
            customerResponse.setNama(customer.getNama());
            customerResponse.setWallet(customer.getWallet());
            return customerResponse;
         }).collect(Collectors.toList());
         responseBody.setTotal(customerRepository.count());
         responseBody.setData(response);
         responseBody.setMessage("Daftar Customer Berhasil Ditampilkan");
         responseBody.setStatusCode(HttpStatus.OK.value());
         responseBody.setStatus(HttpStatus.OK.name());
         return ResponseEntity.ok(responseBody);
      } catch (Exception e) {
         responseBody.setMessage(e.getMessage());
         responseBody.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         responseBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
      }
   }
   

   public ResponseEntity<ResponseBody> getCustomerByQrCode(String qrCode) {
      ResponseBody responseBody = new ResponseBody();
      try {
         CustomerModel customer = customerRepository.findByQrCode(qrCode);
         if (customer != null) {
            CustomerResponse response = new CustomerResponse();
            response.setQrCode(customer.getQrCode());
            response.setNama(customer.getNama());
            response.setWallet(customer.getWallet());
            responseBody.setData(response);
            responseBody.setMessage("Customer Berhasil Ditemukan");
            responseBody.setTotal(1);
            responseBody.setStatusCode(HttpStatus.OK.value());
            responseBody.setStatus(HttpStatus.OK.name());
            return ResponseEntity.ok(responseBody);
         } else {
            responseBody.setMessage("Customer Tidak Ditemukan");
            responseBody.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseBody.setStatus(HttpStatus.NOT_FOUND.name());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
         }
      } catch (Exception e) {
         responseBody.setMessage(e.getMessage());
         responseBody.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         responseBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
      }
   }

}
