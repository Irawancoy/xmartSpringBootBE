package com.backend.xmart.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Table(name = "customer")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "qr_code")
   private String qrCode;

   @Column(name = "nama")
   private String nama;

   @Column(name="wallet")
   private int wallet;


}
