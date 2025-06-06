package org.example.construction.repository;

import org.example.construction.model.Ksm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KsmRepository extends JpaRepository<Ksm, Integer> {
}
