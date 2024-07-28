// Package tempat kelas ini berada
package com.backend.xmart.model;

// Import anotasi dan kelas yang dibutuhkan untuk pemetaan JPA dan fitur Lombok
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

// Menentukan nama tabel dalam database yang dipetakan ke kelas ini
@Table(name = "transaksi")

// Menandakan bahwa kelas ini adalah entitas JPA
@Entity

// Menggunakan anotasi Lombok untuk membuat getter, setter, toString, equals, dan hashCode secara otomatis
@Data

// Menggunakan anotasi Lombok untuk membuat konstruktor tanpa argumen
@NoArgsConstructor

// Menggunakan anotasi Lombok untuk membuat konstruktor dengan semua argumen
@AllArgsConstructor
public class TransaksiModel implements Serializable {

   // Menandakan bahwa field ini adalah primary key dari tabel
   @Id
   // Menentukan strategi pengisian nilai secara otomatis untuk primary key
   @GeneratedValue(strategy = GenerationType.AUTO)
   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name = "transaksi_id")
   private String transaksiId;

   // Menandakan hubungan many-to-one dengan entitas CustomerModel
   @ManyToOne
   // Menentukan kolom di tabel transaksi yang menjadi foreign key yang mengacu ke kolom qr_code di tabel customer
   @JoinColumn(name = "qr_code", referencedColumnName = "qr_code", insertable = false, updatable = false)
   private CustomerModel customer;

   // Menandakan hubungan many-to-one dengan entitas BarangModel
   @ManyToOne
   // Menentukan kolom di tabel transaksi yang menjadi foreign key yang mengacu ke kolom rfid di tabel barang
   @JoinColumn(name = "rfid", referencedColumnName = "rfid", insertable = false, updatable = false)
   private BarangModel barang;

   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name = "harga_satuan")
   private int hargaSatuan;

   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name = "jumlah")
   private int jumlah;

   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name = "date")
   private String date;

}
