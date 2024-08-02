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

@Service
public class BarangService {

   @Autowired
   private BarangRepository barangRepository;

   public ResponseEntity<ResponseBody> getAllBarangs(Pageable pageable) {
      ResponseBody responseBody = new ResponseBody();
      try {
         Page<BarangModel> barangs = barangRepository.findAll(pageable);
         List<BarangResponse> response = barangs.stream().map(barang -> {
            BarangResponse barangResponse = new BarangResponse();
            barangResponse.setRfid(barang.getRfid());
            barangResponse.setNamaBarang(barang.getNamaBarang());
            barangResponse.setHargaSatuan(barang.getHargaSatuan());
            return barangResponse;
         }).collect(Collectors.toList());
         responseBody.setTotal(barangRepository.count());
         responseBody.setData(response);
         responseBody.setMessage("Daftar Barang Berhasil Ditampilkan");
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


   public ResponseEntity<ResponseBody> getBarangByRfid(String rfid) {
      ResponseBody responseBody = new ResponseBody();
      try {
         BarangModel barang = barangRepository.findByRfid(rfid);
         if (barang != null) {
            BarangResponse response = new BarangResponse();
            response.setRfid(barang.getRfid());
            response.setNamaBarang(barang.getNamaBarang());
            response.setHargaSatuan(barang.getHargaSatuan());
            responseBody.setTotal(1);
            responseBody.setData(response);
            responseBody.setMessage("Barang Berhasil Ditemukan");
            responseBody.setStatusCode(HttpStatus.OK.value());
            responseBody.setStatus(HttpStatus.OK.name());
            return ResponseEntity.ok(responseBody);
         } else {
            responseBody.setMessage("Barang Tidak Ditemukan");
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
