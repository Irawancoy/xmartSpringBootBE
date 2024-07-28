package com.backend.xmart.config;

import org.springframework.context.annotation.Configuration; // Mengimpor anotasi @Configuration.
import org.springframework.http.MediaType; // Mengimpor MediaType untuk konfigurasi negosiasi konten.
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer; // Mengimpor ContentNegotiationConfigurer.
import org.springframework.web.servlet.config.annotation.CorsRegistry; // Mengimpor CorsRegistry untuk konfigurasi CORS.
import org.springframework.web.servlet.config.annotation.EnableWebMvc; // Mengimpor anotasi @EnableWebMvc.
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Mengimpor antarmuka WebMvcConfigurer.

@Configuration // Menandai kelas ini sebagai kelas konfigurasi Spring.
@EnableWebMvc // Mengaktifkan dukungan MVC dalam aplikasi Spring.
public class WebMvcConfig implements WebMvcConfigurer { // Mengimplementasikan antarmuka WebMvcConfigurer untuk menyesuaikan konfigurasi MVC.

  @Override
  public void addCorsMappings(CorsRegistry cors) { // Menyesuaikan pengaturan CORS (Cross-Origin Resource Sharing).
    cors
      .addMapping("/**") // Mengizinkan semua rute.
        .allowedOriginPatterns("*") // Mengizinkan semua pola asal.
        .allowedMethods("*") // Mengizinkan semua metode HTTP (GET, POST, PUT, DELETE, dll).
        .allowedHeaders("*") // Mengizinkan semua header.
        .allowCredentials(true) // Mengizinkan pengiriman kredensial (seperti cookies) dalam permintaan.
        .maxAge(3600); // Menetapkan umur maksimum preflight request menjadi 3600 detik (1 jam).
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) { // Menyesuaikan negosiasi konten.
    configurer.defaultContentType(MediaType.APPLICATION_JSON); // Mengatur tipe konten default menjadi JSON.
  }

}
