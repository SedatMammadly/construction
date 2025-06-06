package org.example.construction.repository;

import org.example.construction.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects,Integer> {
    Projects findBySlug(String slug);
}
