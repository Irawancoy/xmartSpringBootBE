package com.backend.xmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.xmart.model.BarangModel;

@Repository
public interface BarangRepository extends JpaRepository<BarangModel, String> {

   // find barang by rfid
   BarangModel findByRfid(String rfid);
}
