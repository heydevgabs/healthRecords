package org.system.health.record.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.system.health.record.dto.RecordRequestDTO;
import org.system.health.record.dto.RecordResponseDTO;
import org.system.health.record.mapper.RecordMapper;
import org.system.health.record.model.RecordEntity;
import org.system.health.record.service.RecordService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService service;
    private final RecordMapper mapper;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public RecordController(RecordService service, RecordMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Upload a CSV file",
            description = "Uploads a CSV file and processes its content to store records in the database.",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary"),
                            examples = @ExampleObject(name = "example.csv", value = "CSV file example")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "CSV file uploaded successfully!"),
                    @ApiResponse(responseCode = "400", description = "Failed to process file.", content = @Content)
            }
    )
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<RecordEntity> records = reader.lines()
                    .skip(1)
                    .map(line -> {
                        try {
                            RecordRequestDTO request = parseCSVLineToRecordRequestDTO(line);
                            return mapper.toEntity(request);
                        } catch (Exception e) {
                            System.err.println("Invalid line skipped: " + line + " - Reason: " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();

            service.saveRecords(records);
            return ResponseEntity.ok("CSV file uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to process file: " + e.getMessage());
        }
    }

    @Operation(
            summary = "Fetch all records",
            description = "Fetches all records from the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of all records",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RecordResponseDTO.class)
                            )
                    )
            }
    )
    @GetMapping
    public List<RecordResponseDTO> getAllRecords() {
        return service.getAllRecords().stream()
                .map(mapper::toResponse)
                .toList();
    }
    @Operation(
            summary = "Fetch a record by code",
            description = "Fetches a specific record by its unique code.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The requested record",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RecordResponseDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Record not found", content = @Content)
            }
    )
    @GetMapping("/{code}")
    public ResponseEntity<RecordResponseDTO> getRecordByCode(@PathVariable String code) {
        return service.getRecordByCode(code)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete all records",
            description = "Deletes all records from the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All records deleted successfully."),
            }
    )
    @DeleteMapping
    public ResponseEntity<String> deleteAllRecords() {
        service.deleteAllRecords();
        return ResponseEntity.ok("All records deleted successfully!");
    }

    private RecordRequestDTO parseCSVLineToRecordRequestDTO(String line) {
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(false)
                    .setTrim(true)
                    .setAllowMissingColumnNames(true)
                    .build();

            CSVParser parser = CSVParser.parse(line, csvFormat);
            CSVRecord csvRecord = parser.iterator().next();

            if (csvRecord.size() != 8) {
                throw new IllegalArgumentException("Insufficient fields in line: " + line);
            }

            return new RecordRequestDTO() {{
                setSource(csvRecord.get(0));
                setCodeListCode(csvRecord.get(1));
                setCode(csvRecord.get(2));
                setDisplayValue(csvRecord.get(3));
                setLongDescription(csvRecord.get(4).isEmpty() ? null : csvRecord.get(4));
                setFromDate(parseDate(csvRecord.get(5)));
                setToDate(parseDate(csvRecord.get(6)));
                setSortingPriority(csvRecord.get(7).isEmpty() ? null : Integer.parseInt(csvRecord.get(7)));
            }};
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing CSV line: " + line, e);
        }
    }

    private String parseDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        }
        try {
            LocalDate parsedDate = LocalDate.parse(date.trim(), DATE_FORMATTER);
            return parsedDate.toString();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date, e);
        }
    }
}
