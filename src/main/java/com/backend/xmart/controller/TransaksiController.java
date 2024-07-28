package com.backend.xmart.controller;

import org.springframework.beans.factory.annotation.Autowired; // Mengimpor anotasi @Autowired untuk dependency injection.
import org.springframework.data.domain.Pageable; // Mengimpor Pageable untuk dukungan pagination.
import org.springframework.data.web.PageableDefault; // Mengimpor PageableDefault untuk menetapkan default pagination.
import org.springframework.http.ResponseEntity; // Mengimpor ResponseEntity untuk membungkus respons HTTP.
import org.springframework.web.bind.annotation.GetMapping; // Mengimpor GetMapping untuk menangani permintaan GET.
import org.springframework.web.bind.annotation.RequestMapping; // Mengimpor RequestMapping untuk menetapkan URL dasar.
import org.springframework.web.bind.annotation.RestController; // Mengimpor RestController untuk mendefinisikan kelas sebagai controller.

import com.backend.xmart.dto.response.ResponseBody; // Mengimpor ResponseBody untuk struktur respons khusus.
import com.backend.xmart.service.TransaksiService; // Mengimpor TransaksiService untuk logika bisnis transaksi.

@RestController // Menandakan bahwa kelas ini adalah controller dan setiap metode di dalamnya akan menghasilkan respons JSON.
@RequestMapping("/transaksi") // Menetapkan URL dasar untuk semua endpoint dalam kelas ini, yaitu /transaksi.
public class TransaksiController {

   @Autowired // Menyuntikkan instance TransaksiService ke dalam TransaksiController.
   private TransaksiService transaksiService;

   // Endpoint untuk mendapatkan semua transaksi dengan pagination
   @GetMapping("/all") // Menangani permintaan HTTP GET ke /transaksi/all.
   public ResponseEntity<ResponseBody> getAllTransaksi(
      @PageableDefault(page = 0, size = 7) // Mengatur default pagination: halaman pertama, ukuran 7.
      Pageable pageable) {
      return transaksiService.getAllTransaksi(pageable); // Memanggil metode getAllTransaksi dari TransaksiService dan mengembalikan hasilnya.
   }

}
