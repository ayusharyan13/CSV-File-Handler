package com.example.assignment.EventStrat.controller;
import com.example.assignment.EventStrat.service.CSVService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@Tag(name = "CSV Uploader API", description = "API for uploading and processing CSV files")
@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    private CSVService csvService;

    @Operation(summary = "Upload a CSV file", description = "Upload a CSV file to process and store its data in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CSV file uploaded and processed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request (e.g., missing file or invalid data)"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a CSV file", HttpStatus.BAD_REQUEST);
        }

        try {
            csvService.save(file);
            return new ResponseEntity<>("CSV file uploaded and processed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process CSV file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}