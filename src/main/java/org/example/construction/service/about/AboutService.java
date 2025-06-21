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
        About about =new About();
        about.setCertificates(certificateRepository.findAll());
        about.setMissions(missionsRepository.findAll());
        about.setValues(valuesRepository.findAll());
        about.setManageTeams(manageTeamRepository.findAll());
        about.setHistory(historyRepository.findFirstByOrderByIdAsc());
        about.setVision(visionRepository.findFirstByOrderByIdAsc());
        about.setManagementStructure(managementStructureRepository.findFirstByOrderByIdAsc());

        return about;
    }
}
