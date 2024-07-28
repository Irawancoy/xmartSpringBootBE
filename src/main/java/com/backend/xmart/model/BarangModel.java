// Package tempat kelas ini berada
package com.backend.xmart.model;

// Import anotasi dan kelas yang dibutuhkan untuk pemetaan JPA dan fitur Lombok
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

// Menentukan nama tabel dalam database yang dipetakan ke kelas ini
@Table(name = "barang")

// Menandakan bahwa kelas ini adalah entitas JPA
@Entity

// Menggunakan anotasi Lombok untuk membuat getter, setter, toString, equals, dan hashCode secara otomatis
@Data

// Menggunakan anotasi Lombok untuk membuat konstruktor tanpa argumen
@NoArgsConstructor

// Menggunakan anotasi Lombok untuk membuat konstruktor dengan semua argumen
@AllArgsConstructor
public class BarangModel implements Serializable {

   // Menandakan bahwa field ini adalah primary key dari tabel
   @Id
   // Menentukan strategi pengisian nilai secara otomatis untuk primary key
   @GeneratedValue(strategy = GenerationType.AUTO)
   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name = "rfid")
   private String rfid;

   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name = "nama_barang")
   private String namaBarang;

   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name="harga_satuan")
   private int hargaSatuan;
}
