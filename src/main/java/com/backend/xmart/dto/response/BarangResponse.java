package com.backend.xmart.dto.response;

import lombok.Data; // Mengimpor anotasi @Data dari Lombok.

@Data // Anotasi Lombok yang menghasilkan getter, setter, toString, equals, dan hashCode secara otomatis.
public class BarangResponse {
   private String rfid; // Menyimpan nilai RFID dari barang.
   private String namaBarang; // Menyimpan nama barang.
   private int hargaSatuan; // Menyimpan harga satuan barang.
}
