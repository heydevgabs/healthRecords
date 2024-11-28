package org.system.health.record.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.system.health.record.model.RecordEntity;
import org.system.health.record.repository.RecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {
    private final RecordRepository repository;

    public RecordService(RecordRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveRecords( List< RecordEntity > records) {
        List<RecordEntity> savedRecords = repository.saveAll(records);
        System.out.println("Saved records: " + savedRecords.size());
    }

    public List<RecordEntity> getAllRecords() {
        return repository.findAll();
    }

    public Optional<RecordEntity> getRecordByCode( String code) {
        return repository.findByCode(code);
    }

    public void deleteAllRecords() {
        repository.deleteAll();
    }
}
