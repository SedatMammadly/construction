package org.example.construction.controller.home;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.LogoDto;
import org.example.construction.model.Logo;
import org.example.construction.repository.LogoRepository;
import org.example.construction.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/logo")
@RequiredArgsConstructor
public class LogoController {

    private final LogoRepository logoRepository;
    private final FileService fileService;

    @GetMapping
    public Logo getLogo() {
        return logoRepository.findFirstByOrderByIdAsc();
    }
    @PostMapping
    public Logo setLogo(@RequestPart LogoDto logoDto, @RequestPart(required = false) MultipartFile icon) throws IOException {
        logoRepository.deleteAll();
        Logo logo=new Logo();
        logo.setTitle(logoDto.getName());
        logo.setImage(fileService.uploadFile(icon));
        return logoRepository.save(logo);
    }
}
