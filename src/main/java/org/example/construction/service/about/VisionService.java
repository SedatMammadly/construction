package org.example.construction.service.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.VisionDto;
import org.example.construction.model.about.Vision;
import org.example.construction.repository.about.VisionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisionService {

    private final VisionRepository repository;

    public Vision save(VisionDto dto) {
        repository.deleteAll(); // Eski tüm vizyonları sil
        Vision vision = new Vision();
        vision.setVision(dto.getVision());
        return repository.save(vision);
    }

    public Vision get() {
        return repository.findAll().stream().findFirst()
                .orElse(null); // DB boşsa null döner, istersen boş nesne de dönebiliriz
    }
}
