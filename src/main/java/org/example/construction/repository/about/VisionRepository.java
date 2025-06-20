package org.example.construction.repository.about;

import org.example.construction.model.about.Vision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisionRepository extends JpaRepository<Vision,Long> {
    Vision findFirstByOrderByIdAsc();

}
