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

@Service // Anotasi ini menandakan bahwa kelas ini adalah service dan akan dikelola oleh Spring.
public class CustomerService {

   @Autowired // Menyuntikkan instance CustomerRepository ke dalam CustomerService.
   private CustomerRepository customerRepository;

   // Metode untuk mendapatkan semua customer dengan pagination
   public ResponseEntity<ResponseBody> getAllCustomers(Pageable pageable) {
      ResponseBody responseBody = new ResponseBody();
      try {
         // Mengambil data customer secara paginated dari repository
         Page<CustomerModel> customers = customerRepository.findAll(pageable);
         
         // Mengubah List<CustomerModel> menjadi List<CustomerResponse> untuk dikembalikan sebagai response
         List<CustomerResponse> response = customers.stream().map(customer -> {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setQrCode(customer.getQrCode());
            customerResponse.setNama(customer.getNama());
            customerResponse.setWallet(customer.getWallet());
            return customerResponse;
         }).collect(Collectors.toList());

         // Mengisi ResponseBody dengan data dan informasi tambahan
         responseBody.setTotal(customerRepository.count());
         responseBody.setData(response);
         responseBody.setMessage("Daftar Customer Berhasil Ditampilkan");
         responseBody.setStatusCode(HttpStatus.OK.value());
         responseBody.setStatus(HttpStatus.OK.name());
         
         // Mengembalikan response dengan status OK
         return ResponseEntity.ok(responseBody);
      } catch (Exception e) {
         // Menangani kesalahan dan mengisi ResponseBody dengan informasi kesalahan
         responseBody.setMessage(e.getMessage());
         responseBody.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         responseBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
         
         // Mengembalikan response dengan status INTERNAL_SERVER_ERROR
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
      }
   }
   
   // Metode untuk menemukan customer berdasarkan QR Code
   public ResponseEntity<ResponseBody> getCustomerByQrCode(String qrCode) {
      ResponseBody responseBody = new ResponseBody();
      try {
         // Mencari customer berdasarkan QR Code
         CustomerModel customer = customerRepository.findByQrCode(qrCode);
         if (customer != null) {
            // Jika customer ditemukan, mengubah CustomerModel menjadi CustomerResponse
            CustomerResponse response = new CustomerResponse();
            response.setQrCode(customer.getQrCode());
            response.setNama(customer.getNama());
            response.setWallet(customer.getWallet());
            responseBody.setData(response);
            responseBody.setMessage("Customer Berhasil Ditemukan");
            responseBody.setTotal(1);
            responseBody.setStatusCode(HttpStatus.OK.value());
            responseBody.setStatus(HttpStatus.OK.name());
            
            // Mengembalikan response dengan status OK
            return ResponseEntity.ok(responseBody);
         } else {
            // Jika customer tidak ditemukan, mengisi ResponseBody dengan pesan not found
            responseBody.setMessage("Customer Tidak Ditemukan");
            responseBody.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseBody.setStatus(HttpStatus.NOT_FOUND.name());
            
            // Mengembalikan response dengan status NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
         }
      } catch (Exception e) {
         // Menangani kesalahan dan mengisi ResponseBody dengan informasi kesalahan
         responseBody.setMessage(e.getMessage());
         responseBody.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
         responseBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
         
         // Mengembalikan response dengan status INTERNAL_SERVER_ERROR
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
      }
   }

}
