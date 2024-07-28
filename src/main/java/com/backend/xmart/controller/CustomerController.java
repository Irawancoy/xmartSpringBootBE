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

@RestController // Anotasi ini menandakan bahwa kelas ini adalah controller dan setiap metode di dalamnya akan menghasilkan respons JSON.
@RequestMapping("/customer") // Menetapkan URL dasar untuk semua endpoint dalam kelas ini, yaitu /customer.
public class CustomerController {

   @Autowired // Menyuntikkan instance CustomerService ke dalam CustomerController.
   private CustomerService customerService;

   // Endpoint untuk mendapatkan semua customer dengan pagination
   @GetMapping("/all") // Menangani permintaan HTTP GET ke /customer/all.
   public ResponseEntity<ResponseBody> getAllCustomers(
      @PageableDefault(page = 0, size = 10, sort = "qrCode") // Mengatur default pagination: halaman pertama, ukuran 10, diurutkan berdasarkan field qrCode.
      Pageable pageable) {
      return customerService.getAllCustomers(pageable); // Memanggil metode getAllCustomers dari CustomerService dan mengembalikan hasilnya.
   }

   // Endpoint untuk menemukan customer berdasarkan QR code
   @GetMapping("/qr-code") // Menangani permintaan HTTP GET ke /customer/qr-code.
   public ResponseEntity<ResponseBody> getCustomerByQrCode(@RequestParam String qrCode) { // Mengambil parameter qrCode dari query string.
      return customerService.getCustomerByQrCode(qrCode); // Memanggil metode getCustomerByQrCode dari CustomerService dan mengembalikan hasilnya.
   }

}
