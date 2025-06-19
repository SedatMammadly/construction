package org.example.construction.repository.about;

import org.example.construction.model.about.AboutUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsRepository extends JpaRepository<AboutUs,Long> {
}
