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
   private String transaksiId;

   @ManyToOne
   @JoinColumn(name = "qr_code", referencedColumnName = "qr_code", insertable = false, updatable = false)
   private CustomerModel customer;

   @ManyToOne
   @JoinColumn(name = "rfid", referencedColumnName = "rfid", insertable = false, updatable = false)
   private BarangModel barang;

   @Column(name = "harga_satuan")
   private int hargaSatuan;

   @Column(name = "jumlah")
   private int jumlah;

   @Column(name = "date")
   private String date;

}
