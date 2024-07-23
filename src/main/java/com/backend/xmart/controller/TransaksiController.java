package com.backend.xmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.xmart.dto.response.ResponseBody;
import com.backend.xmart.service.TransaksiService;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {

   @Autowired
   private TransaksiService transaksiService;

   // get all transaksi
   @GetMapping("/all")
   public ResponseEntity<ResponseBody> getAllTransaksi(
      @PageableDefault(page = 0, size = 7)
         Pageable pageable) {
      return transaksiService.getAllTransaksi(pageable);

   }





}
