package org.example.construction.repository;

import org.example.construction.model.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeAboutRepository extends JpaRepository<About, Long> {
}
