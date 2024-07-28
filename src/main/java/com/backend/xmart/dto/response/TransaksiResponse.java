package com.backend.xmart.dto.response;

import lombok.Data; // Mengimpor anotasi @Data dari Lombok.

@Data // Anotasi Lombok yang menghasilkan getter, setter, toString, equals, dan hashCode secara otomatis.
public class TransaksiResponse {
   private String namaCustomer; // Nama customer yang melakukan transaksi.
   private String namaBarang; // Nama barang yang dibeli.
   private int hargaSatuan; // Harga satuan barang.
   private int jumlah; // Jumlah barang yang dibeli.
   private String date; // Tanggal transaksi.
}
