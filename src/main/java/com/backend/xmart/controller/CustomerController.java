package com.backend.xmart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.backend.xmart.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.backend.xmart.dto.response.ResponseBody;


@RestController
@RequestMapping("/customer")
public class CustomerController {

   @Autowired
   private CustomerService customerService;

   // get all customers
   @GetMapping("/all")
   public ResponseEntity<ResponseBody> getAllCustomers(
      @PageableDefault(page = 0, size = 10, sort = "qrCode")
      Pageable pageable) {
      return customerService.getAllCustomers(pageable);
   }

   // find customer by qr code
   @GetMapping("/qr-code")
   public ResponseEntity<ResponseBody> getCustomerByQrCode(@RequestParam String qrCode) {
      return customerService.getCustomerByQrCode(qrCode);
   }

}
