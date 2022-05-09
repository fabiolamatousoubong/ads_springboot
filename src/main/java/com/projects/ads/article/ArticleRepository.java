package com.projects.ads.article;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT s FROM Article s WHERE s.reference = ?1")
    Optional<Article> findArticleByReference(String reference);
    Optional<Article> findArticleByName(String name);
}
