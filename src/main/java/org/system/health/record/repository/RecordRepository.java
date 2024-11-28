package org.system.health.record.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.system.health.record.model.RecordEntity;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    Optional< RecordEntity > findByCode( String code);
    void deleteAll();
}
