package com.backend.xmart.dto.response;

import lombok.Data;

@Data
public class BarangResponse {
   private String rfid;
   private String namaBarang;
   private int hargaSatuan;

}
