package org.example.construction.service.foreign;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ForeignMissionDto;
import org.example.construction.model.foreign.Content;
import org.example.construction.model.foreign.ForeignMission;
import org.example.construction.repository.foreign.ForeignRepository;
import org.example.construction.service.FileService;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForeignMissionService {
    private final FileService fileService;
    private final ForeignRepository foreignRepository;

    public ForeignMission add(ForeignMissionDto foreignMissionDto, List<MultipartFile> images, MultipartFile icon) throws IOException {
        ForeignMission foreignMission = new ForeignMission();
        Content content = new Content();
        content.setImages(fileService.uploadFiles(images));
        content.setContentWrite(foreignMissionDto.getContent().getContentWrite());
        foreignMission.setIcon(fileService.uploadFile(icon));
        foreignMission.setContent(content);
        foreignMission.setDescription(foreignMissionDto.getDescription());
        foreignMission.setHeader(foreignMissionDto.getHeader());
        foreignMission.setSlug(SlugUtil.toSlug(foreignMissionDto.getHeader()));
        return foreignRepository.save(foreignMission);
    }
    public ForeignMission update(Long id, ForeignMissionDto foreignMissionDto, List<MultipartFile> images, MultipartFile icon) throws IOException {
        ForeignMission foreignMission = foreignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foreign mission not found"));

        if (foreignMissionDto.getHeader() != null)
            foreignMission.setHeader(foreignMissionDto.getHeader());

        if (foreignMissionDto.getDescription() != null)
            foreignMission.setDescription(foreignMissionDto.getDescription());

        if (foreignMissionDto.getContent() != null && foreignMissionDto.getContent().getContentWrite() != null)
            foreignMission.getContent().setContentWrite(foreignMissionDto.getContent().getContentWrite());

        if (images != null && !images.isEmpty())
            foreignMission.getContent().setImages(fileService.uploadFiles(images));

        if (icon != null && !icon.isEmpty())
            foreignMission.setIcon(fileService.uploadFile(icon));

        foreignMission.setSlug(SlugUtil.toSlug(foreignMission.getHeader()));

        return foreignRepository.save(foreignMission);
    }

}
