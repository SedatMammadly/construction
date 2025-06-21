package org.example.construction.service.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.model.about.About;
import org.example.construction.repository.about.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutService {
    private final CertificateRepository certificateRepository;
    private final HistoryRepository historyRepository;
    private final ManageTeamRepository manageTeamRepository;
    private final ManagementStructureRepository managementStructureRepository;
    private final MissionsRepository missionsRepository;
    private final ValuesRepository valuesRepository;
    private final VisionRepository visionRepository;

    public About get() {
        About aboutUs=new About();
        aboutUs.setCertificates(certificateRepository.findAll());
        aboutUs.setMissions(missionsRepository.findAll());
        aboutUs.setValues(valuesRepository.findAll());
        aboutUs.setManageTeams(manageTeamRepository.findAll());
        aboutUs.setHistory(historyRepository.findFirstByOrderByIdAsc());
        aboutUs.setVision(visionRepository.findFirstByOrderByIdAsc());
        aboutUs.setManagementStructure(managementStructureRepository.findFirstByOrderByIdAsc());

        return aboutUs;
    }


}
