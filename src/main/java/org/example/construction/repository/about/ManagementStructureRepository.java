package org.example.construction.repository.about;

import org.example.construction.model.about.ManagementStructure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementStructureRepository extends JpaRepository<ManagementStructure,Long> {
    ManagementStructure findFirstByOrderByIdAsc();

}
