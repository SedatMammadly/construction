package org.example.construction.repository;

import org.example.construction.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Integer> {
    Home findFirstByOrderByIdAsc();

}
