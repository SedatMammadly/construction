package org.example.construction.repository.about;

import org.example.construction.model.about.Certificate;
import org.example.construction.model.about.Missions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionsRepository extends JpaRepository<Missions,Long> {
}
