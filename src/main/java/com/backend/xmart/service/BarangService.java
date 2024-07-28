package com.backend.xmart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.backend.xmart.repository.BarangRepository;
import com.backend.xmart.model.BarangModel;
import com.backend.xmart.dto.response.ResponseBody;
import com.backend.xmart.dto.response.BarangResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service // Anotasi ini menandakan bahwa kelas ini adalah service dan akan dikelola oleh Spring.
public class BarangService {

   @Autowired // Menyuntikkan instance BarangRepository ke dalam BarangService.
   private BarangRepository barangRepository;

   // Metode untuk mendapatkan semua barang dengan pagination
   public ResponseEntity<ResponseBody> getAllBarangs(Pageable pageable) {
      ResponseBody responseBody = new ResponseBody();
      try {
         // Mengambil data barang secara paginated dari repository
         Page<BarangModel> barangs = barangRepository.findAll(pageable);
         
         // Mengubah List<BarangModel> menjadi List<BarangResponse> untuk dikembalikan sebagai response
         List<BarangResponse> response = barangs.stream().map(barang -> {
            BarangResponse barangResponse = new BarangResponse();
            barangResponse.setRfid(barang.getRfid());
            barangResponse.setNamaBarang(barang.getNamaBarang());
            barangResponse.setHargaSatuan(barang.getHargaSatuan());
            return barangResponse;
         }).collect(Collectors.toList());
         
         // Mengisi ResponseBody dengan data dan informasi tambahan
         responseBody.setTotal(barangRepository.count());
         responseBody.setData(response);
         responseBody.setMessage("Daftar Barang Berhasil Ditampilkan");
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

   // Metode untuk menemukan barang berdasarkan RFID
   public ResponseEntity<ResponseBody> getBarangByRfid(String rfid) {
      ResponseBody responseBody = new ResponseBody();
      try {
         // Mencari barang berdasarkan RFID
         BarangModel barang = barangRepository.findByRfid(rfid);
         if (barang != null) {
            // Jika barang ditemukan, mengubah BarangModel menjadi BarangResponse
            BarangResponse response = new BarangResponse();
            response.setRfid(barang.getRfid());
            response.setNamaBarang(barang.getNamaBarang());
            response.setHargaSatuan(barang.getHargaSatuan());
            responseBody.setTotal(1);
            responseBody.setData(response);
            responseBody.setMessage("Barang Berhasil Ditemukan");
            responseBody.setStatusCode(HttpStatus.OK.value());
            responseBody.setStatus(HttpStatus.OK.name());
            
            // Mengembalikan response dengan status OK
            return ResponseEntity.ok(responseBody);
         } else {
            // Jika barang tidak ditemukan, mengisi ResponseBody dengan pesan not found
            responseBody.setMessage("Barang Tidak Ditemukan");
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
