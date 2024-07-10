package com.backend.xmart.dto.response;

import lombok.Data;

@Data
public class CustomerResponse {
   private String qrCode;
   private String nama;
   private int wallet;
}
