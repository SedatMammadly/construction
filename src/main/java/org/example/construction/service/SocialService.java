package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.SocialDto;
import org.example.construction.model.Social;
import org.example.construction.repository.SocialRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialService {

    private final SocialRepository socialRepository;
    private final FileService fileService;

    public Social create(SocialDto dto, MultipartFile image) throws IOException {
        Social social = new Social();
        social.setTitle(dto.getTitle());
social.setLink(dto.getLink());
        if (image != null) {
            // Örneğin dosya adını kaydet
            social.setImage(fileService.uploadFile(image));
            // gerçekte dosyayı diske kaydetme vs. burada yapılabilir
        }

        return socialRepository.save(social);
    }

    public List<Social> getAll() {
        return socialRepository.findAll();
    }

    public Social getById(Integer id) {
        return socialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Social not found"));
    }

    public Social update(Integer id, SocialDto dto, MultipartFile image) throws IOException {
        Social social = getById(id);
        social.setTitle(dto.getTitle());
        social.setLink(dto.getLink());

        if (image != null) {
            social.setImage(fileService.uploadFile(image));
        }
        return socialRepository.save(social);
    }

    public void delete(Integer id) {
        socialRepository.deleteById(id);
    }
}

