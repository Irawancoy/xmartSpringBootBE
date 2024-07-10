package com.backend.xmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.xmart.model.TransaksiModel;

@Repository
public interface TransaksiRepository extends JpaRepository<TransaksiModel, Integer>{

}
