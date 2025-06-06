package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutUsRequest;
import org.example.construction.dto.ManageTeamDto;
import org.example.construction.dto.MissionsDto;
import org.example.construction.dto.ValuesDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.AboutUs;
import org.example.construction.model.Values;
import org.example.construction.pojo.*;
import org.example.construction.repository.AboutUsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
                String image = fileService.uploadFile(teamImages.get(i),"image");
                aboutUs.getManageTeams().get(i).setImage(image);
            }
        }
        if (!missionIcons.isEmpty()) {
            for (int i = 0; i < aboutUs.getMissions().size(); i++) {
                String image = fileService.uploadFile(missionIcons.get(i),"image");
                aboutUs.getMissions().get(i).setIcon(image);
            }
        }
        if (!certificates.isEmpty()) {
            for (MultipartFile certificate : certificates) {
                String image = fileService.uploadFile(certificate,"image");
                aboutUs.getCertificates().add(image);
            }
        }
        if (!valueIcons.isEmpty()) {
            for (int i = 0; i < aboutUs.getValues().size(); i++) {
                String image = fileService.uploadFile(valueIcons.get(i),"image");
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
            String image = fileService.uploadFile(icon,"image");
            aboutUs.getMissions().getLast().setIcon(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs updateMission(MissionsDto missionsDto, MultipartFile icon, int index) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        Missions missions = pojoMapper.missionsDtoToPojo(missionsDto);
        aboutUs.getMissions().get(realIndex).setTitle(missions.getTitle());
        aboutUs.getMissions().get(realIndex).setDescription(missions.getDescription());
        if (icon != null) {
            String image = fileService.uploadFile(icon,"image");
            aboutUs.getMissions().get(realIndex).setIcon(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs deleteMission(int index) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        String icon = aboutUs.getMissions().get(realIndex).getIcon();
        if (icon != null) {
            fileService.removeFile(icon);
        }
        aboutUs.getMissions().remove(realIndex);
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs addNewValue(ValuesDto valuesDto, MultipartFile icon) {
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        Values values = pojoMapper.valuesDtoToPojo(valuesDto);
        aboutUs.getValues().add(values);
        if (icon != null) {
            String image = fileService.uploadFile(icon,"image");
            aboutUs.getValues().getLast().setIcon(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs updateValue(ValuesDto valuesDto, MultipartFile icon, int index) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        Values values = pojoMapper.valuesDtoToPojo(valuesDto);
        aboutUs.getValues().get(realIndex).setParagraph(values.getParagraph());
        aboutUs.getValues().get(realIndex).setTitle(values.getTitle());
        if (icon != null) {
            String image = fileService.uploadFile(icon,"image");
            aboutUs.getValues().get(realIndex).setIcon(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs deleteValue(int index) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        String icon = aboutUs.getValues().get(realIndex).getIcon();
        if (icon != null) {
            fileService.removeFile(icon);
        }
        aboutUs.getValues().remove(realIndex);
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

    public AboutUs updateHistory(History history, int index) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        aboutUs.getHistory().set(realIndex, history.getHistory());
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs updateManageTeam(int index, ManageTeamDto manageTeamDto, MultipartFile image) {
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        ManageTeam manageTeam = pojoMapper.manageTeamDtoToPojo(manageTeamDto);
        if (manageTeam != null) {
            aboutUs.getManageTeams().get(realIndex).setTitle(manageTeam.getTitle());
            aboutUs.getManageTeams().get(realIndex).setParagraph(manageTeam.getParagraph());
        }

        if (image != null) {
            String currentImage = aboutUs.getManageTeams().get(realIndex).getImage();
            fileService.removeFile(currentImage);
            String newImage = fileService.uploadFile(image,"image");
            aboutUs.getManageTeams().get(realIndex).setImage(newImage);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs updateManagementStructure(ManagementStructure managementStructure) {
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        if (managementStructure != null) {
            aboutUs.setManagementStructure(managementStructure.getParagraph());
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs addCertificate(MultipartFile file) {
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        if (file != null) {
            String image = fileService.uploadFile(file,"image");
            aboutUs.getCertificates().add(image);
        }
        return aboutUsRepository.save(aboutUs);
    }

    public AboutUs deleteCertificate(int index){
        int realIndex = index - 1;
        AboutUs aboutUs = aboutUsRepository.findAll().getFirst();
        String image = aboutUs.getCertificates().get(realIndex);
        fileService.removeFile(image);
        aboutUs.getCertificates().remove(index);
      return aboutUsRepository.save(aboutUs);
    }
}
