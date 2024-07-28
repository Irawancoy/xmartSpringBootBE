package com.backend.xmart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.backend.xmart.service.BarangService;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.backend.xmart.dto.response.ResponseBody;

@RestController // Anotasi ini menandakan bahwa kelas ini adalah controller dan setiap metode di dalamnya akan menghasilkan respons JSON.
@RequestMapping("/barang") // Menetapkan URL dasar untuk semua endpoint dalam kelas ini, yaitu /barang.
public class BarangController {

   @Autowired // Menyuntikkan instance BarangService ke dalam BarangController.
   private BarangService barangService;

   // Endpoint untuk mendapatkan semua barang dengan pagination
   @GetMapping("/all") // Menangani permintaan HTTP GET ke /barang/all.
   public ResponseEntity<ResponseBody> getAllBarangs(
      @PageableDefault(page = 0, size = 10, sort = "rfid") // Mengatur default pagination: halaman pertama, ukuran 10, diurutkan berdasarkan field rfid.
      Pageable pageable) {
      return barangService.getAllBarangs(pageable); // Memanggil metode getAllBarangs dari BarangService dan mengembalikan hasilnya.
   }

   // Endpoint untuk menemukan barang berdasarkan RFID
   @GetMapping("/rfid") // Menangani permintaan HTTP GET ke /barang/rfid.
   public ResponseEntity<ResponseBody> getBarangByRfid(@RequestParam String rfid) { // Mengambil parameter rfid dari query string.
      return barangService.getBarangByRfid(rfid); // Memanggil metode getBarangByRfid dari BarangService dan mengembalikan hasilnya.
   }

}
