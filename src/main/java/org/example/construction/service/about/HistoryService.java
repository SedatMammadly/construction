package org.example.construction.service.about;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.HistoryDto;
import org.example.construction.model.about.History;
import org.example.construction.repository.about.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository repository;

    public History save(HistoryDto dto) {
        repository.deleteAll();
        History history = new History();
        history.setHistory(dto.getHistory());
        return repository.save(history);
    }

    public History get() {
        return repository.findAll().stream().findFirst().orElse(null);
    }
}
