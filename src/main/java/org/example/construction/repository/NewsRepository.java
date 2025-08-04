package org.example.construction.repository;

import org.example.construction.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    List<News> findTop10ByOrderByCreatedAtDesc();

    List<News> findAllByOrderByCreatedAtDesc();

    News findBySlug(String slug);
}
