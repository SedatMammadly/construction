package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutUsRequest;
import org.example.construction.dto.MissionsDto;
import org.example.construction.dto.ValuesDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.AboutUs;
import org.example.construction.model.Values;
import org.example.construction.pojo.History;
import org.example.construction.pojo.Missions;
import org.example.construction.pojo.Vision;
import org.example.construction.repository.AboutUsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AboutUsService {
    private final AboutUsRepository aboutUsRepository;
    private final PageMapper pageMapper;
    private final FileService fileService;
    private final PojoMapper pojoMapper;


    public AboutUs getAboutUsPage() {
        return aboutUsRepository.findAll().getFirst();
    }


    public AboutUs createAboutUs(AboutUsRequest aboutUsRequest, List<MultipartFile> teamImages,
                                 List<MultipartFile> missionIcons, List<MultipartFile> certificates, List<MultipartFile> valueIcons) {
        AboutUs aboutUs = pageMapper.aboutUsDtoToEntity(aboutUsRequest);
        if (!teamImages.isEmpty()) {
            for (int i = 0; i < aboutUs.getManageTeams().size(); i++) {
                String image = fileService.uploadFile(teamImages.get(i));
                aboutUs.getManageTeams().get(i).setImage(image);
            }
        }
        if (!missionIcons.isEmpty()) {
            for (int i = 0; i < aboutUs.getMissions().size(); i++) {
                String image = fileService.uploadFile(missionIcons.get(i));
                aboutUs.getMissions().get(i).setIcon(image);
            }
        }
        if (!certificates.isEmpty()) {
            for (MultipartFile certificate : certificates) {
                String image = fileService.uploadFile(certificate);
                aboutUs.getCertificates().add(image);
            }
        }
        if (!valueIcons.isEmpty()) {
            for (int i = 0; i < aboutUs.getValues().size(); i++) {
                String image = fileService.uploadFile(valueIcons.get(i));
                aboutUs.getValues().get(i).setIcon(image);
            }
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs addNewMission(MissionsDto missionsDto, MultipartFile icon) {
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        Missions missions = pojoMapper.missionsDtoToPojo(missionsDto);
        aboutUs.getMissions().add(missions);
        if (icon != null) {
            String image = fileService.uploadFile(icon);
            aboutUs.getMissions().getLast().setIcon(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs updateMission(MissionsDto missionsDto, MultipartFile icon, int index) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        Missions missions = pojoMapper.missionsDtoToPojo(missionsDto);
        aboutUs.getMissions().add(realIndex, missions);
        if (icon != null) {
            String image = fileService.uploadFile(icon);
            aboutUs.getMissions().get(realIndex).setIcon(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs addNewValue(ValuesDto valuesDto, MultipartFile icon) {
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        Values values = pojoMapper.valuesDtoToPojo(valuesDto);
        aboutUs.getValues().add(values);
        if (icon != null) {
            String image = fileService.uploadFile(icon);
            aboutUs.getValues().getLast().setIcon(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs updateValue(ValuesDto valuesDto, MultipartFile icon, int index) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        Values values = pojoMapper.valuesDtoToPojo(valuesDto);
        aboutUs.getValues().add(realIndex, values);
        if (icon != null) {
            String image = fileService.uploadFile(icon);
            aboutUs.getValues().get(realIndex).setIcon(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs updateVision(Vision vision) {
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        aboutUs.setVisions(vision.getVision());
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs addHistory(History history) {
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        aboutUs.getHistory().add(history.getHistory());
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs updateHistory(History history,int index) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        aboutUs.getHistory().set(realIndex, history.getHistory());
        return aboutUsRepository.save(aboutUs);
    }
}
