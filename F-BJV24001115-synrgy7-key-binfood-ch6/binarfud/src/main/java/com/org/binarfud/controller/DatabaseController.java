// package com.org.binarfud.controller;

// import com.org.binarfud.service.DatabaseService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/database")
// public class DatabaseController {

//     @Autowired
//     private DatabaseService databaseService;

//     @DeleteMapping("/delete-all-data")
//     public ResponseEntity<Void> deleteAllData() {
//         databaseService.deleteAllData();
//         return ResponseEntity.noContent().build();
//     }
// }
