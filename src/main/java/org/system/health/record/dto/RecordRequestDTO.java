package org.system.health.record.dto;

import lombok.Data;

@Data
public class RecordRequestDTO {
    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private Integer sortingPriority;
}