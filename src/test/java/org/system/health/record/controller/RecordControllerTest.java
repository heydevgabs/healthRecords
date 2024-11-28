package org.system.health.record.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.system.health.record.dto.RecordResponseDTO;
import org.system.health.record.mapper.RecordMapper;
import org.system.health.record.model.RecordEntity;
import org.system.health.record.service.RecordService;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class RecordControllerTest {
    @Mock
    private RecordService service;

    @Mock
    private RecordMapper mapper;

    @InjectMocks
    private RecordController controller;

    public RecordControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadCSV_Success() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.csv",
                "text/csv",
                "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority\n".getBytes()
        );

        RecordEntity mockEntity = new RecordEntity();
        when(mapper.toEntity(any())).thenReturn(mockEntity);
        doNothing().when(service).saveRecords(anyList());

        ResponseEntity<String> response = controller.uploadCSV(file);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("CSV file uploaded successfully!"));
        verify(service, times(1)).saveRecords(anyList());
    }

    @Test
    void testGetAllRecords_Success() {
        RecordResponseDTO record1 = new RecordResponseDTO("source1", "codeListCode1", "code1", "displayValue1", "longDescription1", "2023-01-01", "2023-12-31", 1);
        RecordResponseDTO record2 = new RecordResponseDTO("source2", "codeListCode2", "code2", "displayValue2", null, "2023-01-01", null, null);

        when(service.getAllRecords()).thenReturn( List.of(new RecordEntity(), new RecordEntity()));
        when(mapper.toResponse(any(RecordEntity.class))).thenReturn(record1, record2);

        ResponseEntity<List<RecordResponseDTO>> response = ResponseEntity.ok(controller.getAllRecords());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), hasSize(2));
        assertThat( Objects.requireNonNull( response.getBody( ) ).get(0).getSource(), is("source1"));
        assertThat(response.getBody().get(1).getCode(), is("code2"));
        verify(service, times(1)).getAllRecords();
    }

    @Test
    void testGetRecordByCode_Success() {
        RecordEntity mockEntity = new RecordEntity();
        RecordResponseDTO mockResponse = new RecordResponseDTO("source", "codeListCode", "code", "displayValue", null, "2023-01-01", null, null);

        when(service.getRecordByCode("test-code")).thenReturn(Optional.of(mockEntity));
        when(mapper.toResponse(mockEntity)).thenReturn(mockResponse);

        ResponseEntity<RecordResponseDTO> response = controller.getRecordByCode("test-code");

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat( Objects.requireNonNull( response.getBody( ) ).getCode(), is("code"));
        verify(service, times(1)).getRecordByCode("test-code");
    }

    @Test
    void testGetRecordByCode_NotFound() {
        when(service.getRecordByCode("invalid-code")).thenReturn( Optional.empty());

        ResponseEntity< RecordResponseDTO > response = controller.getRecordByCode("invalid-code");

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        verify(service, times(1)).getRecordByCode("invalid-code");
    }

    @Test
    void testDeleteAllRecords_Success() {
        doNothing().when(service).deleteAllRecords();

        ResponseEntity<String> response = controller.deleteAllRecords();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("All records deleted successfully!"));
        verify(service, times(1)).deleteAllRecords();
    }
}
