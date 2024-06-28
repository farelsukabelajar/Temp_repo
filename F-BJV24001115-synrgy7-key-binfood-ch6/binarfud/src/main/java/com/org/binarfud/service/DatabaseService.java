// package com.org.binarfud.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class DatabaseService {

//     @Autowired
//     private JdbcTemplate jdbcTemplate;

//     public void deleteAllData() {
//         // Mengambil semua tabel dalam database
//         List<String> tables = jdbcTemplate.queryForList(
//             "SELECT tablename FROM pg_tables WHERE schemaname = 'public';", String.class
//         );

//         // Menghapus semua data dari setiap tabel
//         for (String table : tables) {
//             jdbcTemplate.execute("TRUNCATE TABLE " + table + " CASCADE;");
//         }
//     }
// }
