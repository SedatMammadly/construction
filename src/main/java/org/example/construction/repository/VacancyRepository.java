package org.example.construction.repository;

import org.example.construction.model.Special;
import org.example.construction.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    Vacancy findBySlug(String slug);

}
