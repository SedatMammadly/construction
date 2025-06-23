package org.example.construction.repository;


import org.example.construction.model.Logo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogoRepository extends JpaRepository<Logo, Integer> {
    Logo findFirstByOrderByIdAsc();

}
