package com.backend.xmart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.backend.xmart.repository.TransaksiRepository;
import com.backend.xmart.dto.response.TransaksiResponse;
import com.backend.xmart.model.TransaksiModel;
import org.springframework.data.domain.Pageable;
import com.backend.xmart.dto.response.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

@Service // Anotasi ini menandakan bahwa kelas ini adalah service dan akan dikelola oleh Spring.
public class TransaksiService {

   @Autowired // Menyuntikkan instance TransaksiRepository ke dalam TransaksiService.
   private TransaksiRepository transaksiRepository;

   // Metode untuk mendapatkan semua transaksi dengan pagination
   public ResponseEntity<ResponseBody> getAllTransaksi(Pageable pageable) {
      ResponseBody responseBody = new ResponseBody();
      try {
         // Mengambil data transaksi secara paginated dari repository
         Page<TransaksiModel> transaksi = transaksiRepository.findAll(pageable);
         
         // Mengubah List<TransaksiModel> menjadi List<TransaksiResponse> untuk dikembalikan sebagai response
         List<TransaksiResponse> response = transaksi.stream().map(trans -> {
            TransaksiResponse transaksiResponse = new TransaksiResponse();
            transaksiResponse.setNamaCustomer(trans.getCustomer().getNama());
            transaksiResponse.setNamaBarang(trans.getBarang().getNamaBarang());
            transaksiResponse.setHargaSatuan(trans.getHargaSatuan());
            transaksiResponse.setJumlah(trans.getJumlah());
            transaksiResponse.setDate(trans.getDate());
            return transaksiResponse;
         }).collect(Collectors.toList());

         // Mengisi ResponseBody dengan data dan informasi tambahan
         responseBody.setTotal(transaksiRepository.count());
         responseBody.setData(response);
         responseBody.setMessage("Daftar Transaksi Berhasil Ditampilkan");
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
}
