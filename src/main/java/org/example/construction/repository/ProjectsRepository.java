package org.example.construction.repository;

import org.example.construction.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects,Integer> {
    Projects findBySlug(String slug);

    List<Projects> findTop10ByOrderByCreatedAtDesc();
}
