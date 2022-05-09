package com.projects.ads.article;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findArticleByReference(String reference);
    Optional<Article> findArticleByName(String name);
}
