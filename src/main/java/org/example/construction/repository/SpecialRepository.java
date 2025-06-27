package org.example.construction.repository;


import org.example.construction.model.Special;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;

public interface SpecialRepository extends JpaRepository<Special, Long> {
    Special findBySlug(String slug);
}
