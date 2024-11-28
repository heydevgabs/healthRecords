package org.system.health.record.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponseDTO {
    private String source = "";
    private String codeListCode = "";
    private String code = "";
    private String displayValue = "";
    private String longDescription = "";
    private String fromDate = "";
    private String toDate = "";
    private Integer sortingPriority;
}