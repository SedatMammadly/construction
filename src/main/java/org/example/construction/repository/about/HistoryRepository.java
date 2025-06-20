package org.example.construction.repository.about;

import org.example.construction.model.about.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Long> {
    History findFirstByOrderByIdAsc();

}
