package com.backend.xmart.dto.response;

import lombok.Data; // Mengimpor anotasi @Data dari Lombok.

@Data // Anotasi Lombok yang menghasilkan getter, setter, toString, equals, dan hashCode secara otomatis.
public class ResponseBody {
	private long total; // Menyimpan total jumlah item atau entri yang relevan dengan respons.
	private Object data; // Menyimpan data respons yang bisa berupa tipe data apa saja.
	private String message; // Menyimpan pesan yang menyertai respons, bisa berupa pesan sukses atau pesan kesalahan.
	private int statusCode; // Menyimpan kode status HTTP yang merepresentasikan status respons.
	private String status; // Menyimpan status HTTP sebagai string, seperti "OK" atau "INTERNAL_SERVER_ERROR".
}
