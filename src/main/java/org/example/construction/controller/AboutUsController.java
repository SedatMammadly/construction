package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.*;
import org.example.construction.model.AboutUs;
import org.example.construction.model.Home;
import org.example.construction.pojo.History;
import org.example.construction.pojo.ManagementStructure;
import org.example.construction.pojo.Vision;
import org.example.construction.service.AboutUsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/v1/aboutUs")
@RequiredArgsConstructor
public class AboutUsController {
    private final AboutUsService aboutUsService;

    @GetMapping
    public ResponseEntity<AboutUs> getAboutUsPage() {
        return ResponseEntity.ok(aboutUsService.getAboutUsPage());
    }

    @PostMapping
    public ResponseEntity<AboutUs> createAboutUs(@RequestPart(name = "request") AboutUsRequest aboutUsRequest,
                                                 @RequestPart(required = false) List<MultipartFile> teamImages,
                                                 @RequestPart(required = false) List<MultipartFile> missionIcons,
                                                 @RequestPart(required = false) List<MultipartFile> certificates,
                                                 @RequestPart(required = false) List<MultipartFile> valueIcons) {
        return ResponseEntity.ok(aboutUsService.createAboutUs(aboutUsRequest,
                teamImages,
                missionIcons, certificates, valueIcons));
    }

    @PutMapping("/update/missions/add")
    public ResponseEntity<AboutUs> addMissions(@RequestPart(name = "request") MissionsDto missionsDto,
                                               @RequestPart(required = false) MultipartFile icon) {
        return ResponseEntity.ok(aboutUsService.addNewMission(missionsDto, icon));
    }

    @PutMapping("/update/missions/{index}")
    public ResponseEntity<AboutUs> updateMission(@PathVariable int index, @RequestPart(required = false, name = "request") MissionsDto missionsDto,
                                                 @RequestPart(required = false) MultipartFile icon) {
        return ResponseEntity.ok(aboutUsService.updateMission(missionsDto, icon, index));
    }

    @PutMapping("/update/values/add")
    public ResponseEntity<AboutUs> addValue(@RequestPart(name = "request") ValuesDto valuesDto,
                                            @RequestPart(required = false) MultipartFile icon) {
        return ResponseEntity.ok(aboutUsService.addNewValue(valuesDto, icon));
    }

    @PutMapping("/update/values/{index}")
    public ResponseEntity<AboutUs> updateValue(@PathVariable int index, @RequestPart(required = false, name = "request") ValuesDto valuesDto,
                                               @RequestPart(required = false) MultipartFile icon) {
        return ResponseEntity.ok(aboutUsService.updateValue(valuesDto, icon, index));
    }

    @DeleteMapping("/delete/values/{index}")
    public ResponseEntity<AboutUs> deleteValue(@PathVariable int index) {
        return ResponseEntity.ok(aboutUsService.deleteValue(index));
    }

    @DeleteMapping("/delete/missions/{index}")
    public ResponseEntity<AboutUs> deleteMission(@PathVariable int index) {
        return ResponseEntity.ok(aboutUsService.deleteMission(index));
    }

    @PutMapping("/update/vision")
    public ResponseEntity<AboutUs>updateVision(@RequestBody Vision vision) {
       return  ResponseEntity.ok(aboutUsService.updateVision(vision));
    }

    @PutMapping("/update/history/add")
    public ResponseEntity<AboutUs>addNewHistory(@RequestBody History history) {
        return ResponseEntity.ok(aboutUsService.addHistory(history));
    }

    @PutMapping("/update/history/{index}")
    public ResponseEntity<AboutUs>addNewHistory(@RequestBody History history, @PathVariable int index) {
        return ResponseEntity.ok(aboutUsService.updateHistory(history, index));
    }

    @PutMapping("/update/teamManage/{index}")
    public ResponseEntity<AboutUs>updateManageTeam(@PathVariable int index,
                                                   @RequestPart(required = false,name = "request") ManageTeamDto manageTeamDto,
                                                   @RequestPart(required = false) MultipartFile image ) {
        return ResponseEntity.ok(aboutUsService.updateManageTeam(index,manageTeamDto,image));
    }

    @PutMapping("/update/manageStructure")
    public ResponseEntity<AboutUs> updateManagementStructure(@RequestBody ManagementStructure managementStructure) {
        return ResponseEntity.ok(aboutUsService.updateManagementStructure(managementStructure));
    }

    @PutMapping("/update/certificates/add")
    public ResponseEntity<AboutUs> addNewCertificate(@RequestPart MultipartFile certificate) {
        return ResponseEntity.ok(aboutUsService.addCertificate(certificate));
    }

    @DeleteMapping("/update/certificates/delete/{index}")
    public ResponseEntity<AboutUs> deleteCertificate(@PathVariable int index) {
        return ResponseEntity.ok(aboutUsService.deleteCertificate(index));
    }

}
