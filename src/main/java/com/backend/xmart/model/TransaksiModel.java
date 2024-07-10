package com.backend.xmart.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Table(name = "transaksi")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaksiModel implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "transaksi_id")
   private int transaksiId;

   @Column(name = "qr_code")
   private String qrCode;

   @Column(name = "rfid")
   private String rfid;

   @Column(name = "harga_satuan")
   private int hargaSatuan;

   @Column(name = "jumlah")
   private int jumlah;

   @Column(name = "date")
   private String date;

}
