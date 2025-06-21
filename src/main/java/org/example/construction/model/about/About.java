package org.example.construction.model.about;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class About {
    List<Missions> missions;
    Vision vision;
    List<Values> values;
    History history;
    List<ManageTeam> manageTeams;
    ManagementStructure managementStructure;
    List<Certificate> certificates;
}
