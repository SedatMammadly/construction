package org.example.construction.repository.setem;

import lombok.Data;
import org.example.construction.model.setem.Setem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SetemRepository extends JpaRepository<Setem,Long> {
}
