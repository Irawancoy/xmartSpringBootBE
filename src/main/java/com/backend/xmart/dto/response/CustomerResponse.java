package com.backend.xmart.dto.response;

import lombok.Data; // Mengimpor anotasi @Data dari Lombok.

@Data // Anotasi Lombok yang menghasilkan getter, setter, toString, equals, dan hashCode secara otomatis.
public class CustomerResponse {
   private String qrCode; // Menyimpan nilai QR code dari customer.
   private String nama; // Menyimpan nama customer.
   private int wallet; // Menyimpan saldo wallet customer.
}
