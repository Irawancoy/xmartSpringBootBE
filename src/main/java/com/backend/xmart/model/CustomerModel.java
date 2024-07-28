// Package tempat kelas ini berada
package com.backend.xmart.model;

// Import anotasi dan kelas yang dibutuhkan untuk pemetaan JPA dan fitur Lombok
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

// Menentukan nama tabel dalam database yang dipetakan ke kelas ini
@Table(name = "customer")

// Menandakan bahwa kelas ini adalah entitas JPA
@Entity

// Menggunakan anotasi Lombok untuk membuat getter, setter, toString, equals, dan hashCode secara otomatis
@Data

// Menggunakan anotasi Lombok untuk membuat konstruktor tanpa argumen
@NoArgsConstructor

// Menggunakan anotasi Lombok untuk membuat konstruktor dengan semua argumen
@AllArgsConstructor
public class CustomerModel implements Serializable {

   // Menandakan bahwa field ini adalah primary key dari tabel
   @Id
   // Menentukan strategi pengisian nilai secara otomatis untuk primary key
   @GeneratedValue(strategy = GenerationType.AUTO)
   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name = "qr_code")
   private String qrCode;

   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name = "nama")
   private String nama;

   // Menentukan nama kolom dalam tabel yang dipetakan ke field ini
   @Column(name="wallet")
   private int wallet;
}
