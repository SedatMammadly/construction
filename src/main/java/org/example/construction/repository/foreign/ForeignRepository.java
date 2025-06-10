package org.example.construction.repository.foreign;

import org.example.construction.model.foreign.ForeignMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ForeignRepository extends JpaRepository<ForeignMission,Long> {
    Optional<ForeignMission> findBySlug(String slug);
}
