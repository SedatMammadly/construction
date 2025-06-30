package org.example.construction.repository;


import org.example.construction.model.Social;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<Social, Integer> {
    Social findFirstByOrderByIdAsc();

}
