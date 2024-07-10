package com.backend.xmart.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Table(name = "barang")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarangModel implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "rfid")
   private String rfid;

   @Column(name = "nama_barang")
   private String namaBarang;

   @Column(name="harga_satuan")
   private int hargaSatuan;

}
