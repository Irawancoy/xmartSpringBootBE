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

@RestController
@RequestMapping("/barang")
public class BarangController {

   @Autowired
   private BarangService barangService;

   // get all barangs
   @GetMapping("/all")
   public ResponseEntity<ResponseBody> getAllBarangs(
      @PageableDefault(page = 0, size = 10, sort = "rfid")
      Pageable pageable) {
      return barangService.getAllBarangs(pageable);
   }

   // find barang by rfid
   @GetMapping("/rfid")
   public ResponseEntity<ResponseBody> getBarangByRfid(@RequestParam String rfid) {
      return barangService.getBarangByRfid(rfid);
   }

}
