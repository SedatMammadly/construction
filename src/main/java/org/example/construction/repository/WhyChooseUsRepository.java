package org.example.construction.repository;

import org.example.construction.model.WhyChooseUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhyChooseUsRepository extends JpaRepository<WhyChooseUs, Long> {
}
