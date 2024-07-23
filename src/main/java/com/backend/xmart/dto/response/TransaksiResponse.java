package com.backend.xmart.dto.response;

import lombok.Data;


@Data
public class TransaksiResponse {
   private String namaCustomer;
   private String namaBarang;
   private int hargaSatuan;
   private int jumlah;
   private String date;
}
