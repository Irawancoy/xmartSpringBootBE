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

@Service
public class TransaksiService {

   @Autowired
   private TransaksiRepository transaksiRepository;

   // get all transaksi
   public ResponseEntity<ResponseBody> getAllTransaksi(Pageable pageable) {
      ResponseBody responseBody = new ResponseBody();
      try {
         Page<TransaksiModel> transaksi = transaksiRepository.findAll(pageable);
         List<TransaksiResponse> response = transaksi.stream().map(trans -> {
            TransaksiResponse transaksiResponse = new TransaksiResponse();
            transaksiResponse.setNamaCustomer(trans.getCustomer().getNama());
            transaksiResponse.setNamaBarang(trans.getBarang().getNamaBarang());
            transaksiResponse.setHargaSatuan(trans.getHargaSatuan());
            transaksiResponse.setJumlah(trans.getJumlah());
            transaksiResponse.setDate(trans.getDate());
            return transaksiResponse;
         }).collect(Collectors.toList());

         responseBody.setTotal(transaksiRepository.count());
         responseBody.setData(response);
         responseBody.setMessage("Dafar Transaksi Berhasil Ditampilkan");
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
}
