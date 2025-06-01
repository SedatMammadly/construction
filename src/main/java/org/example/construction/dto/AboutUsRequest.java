package org.example.construction.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsRequest {
    List<MissionsDto> missions;
    String visions;
    List<ValuesDto> values;
    List<String>history;
    List<ManageTeamDto> manageTeams;
    String managementStructure;
}
