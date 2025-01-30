package com.example.assignment.EventStrat.service;
import com.example.assignment.EventStrat.exception.MissingMandatoryFieldException;
import com.example.assignment.EventStrat.model.User;
import com.example.assignment.EventStrat.repository.UserRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
@Service
public class CSVService {

    @Autowired
    private UserRepo userRepository;

    public void save(MultipartFile file) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                // Validate mandatory fields
                String name = csvRecord.get("Name");
                String email = csvRecord.get("Email");

                if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
                    throw new MissingMandatoryFieldException("Mandatory fields (Name, Email) are missing in the CSV file");
                }

                // Create or update user
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setContact(csvRecord.get("Contact"));
                user.setCity(csvRecord.get("City"));
                user.setState(csvRecord.get("State"));
                user.setCountry(csvRecord.get("Country"));
                user.setAddress(csvRecord.get("Address"));

                // Check if email already exists
                User existingUser = userRepository.findByEmail(user.getEmail());
                if (existingUser != null) {
                    // Update existing user
                    existingUser.setName(user.getName());
                    existingUser.setContact(user.getContact());
                    existingUser.setCity(user.getCity());
                    existingUser.setState(user.getState());
                    existingUser.setCountry(user.getCountry());
                    existingUser.setAddress(user.getAddress());
                    userRepository.save(existingUser);
                } else {
                    // Save new user
                    userRepository.save(user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}