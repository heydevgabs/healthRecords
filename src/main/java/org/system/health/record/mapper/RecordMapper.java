package org.system.health.record.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.system.health.record.dto.RecordRequestDTO;
import org.system.health.record.dto.RecordResponseDTO;
import org.system.health.record.model.RecordEntity;

@Mapper(componentModel = "spring")
public interface RecordMapper {

    RecordEntity toEntity( RecordRequestDTO request);

    @Mapping(target = "fromDate", source = "fromDate", qualifiedByName = "localDateToString")
    @Mapping(target = "toDate", source = "toDate", qualifiedByName = "localDateToString")
    RecordResponseDTO toResponse(RecordEntity entity);

    @Named("localDateToString")
    static String localDateToString(java.time.LocalDate date) {
        return date != null ? date.toString() : null;
    }
}