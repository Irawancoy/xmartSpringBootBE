// Package tempat interface ini berada
package com.backend.xmart.repository;

// Import kelas-kelas yang dibutuhkan untuk repository JPA
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Import model yang digunakan oleh repository
import com.backend.xmart.model.BarangModel;

// Menandakan bahwa interface ini adalah komponen Spring yang akan di-scan dan dikelola oleh Spring Container
@Repository
public interface BarangRepository extends JpaRepository<BarangModel, String> {

   // Method untuk mencari barang berdasarkan RFID
   BarangModel findByRfid(String rfid);
}
