package com.projects.ads.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
    
    public void addArticle(Article article) {
        Optional<Article> articleReferenceOptional = articleRepository.findArticleByReference(article.getReference());
        Optional<Article> articleNameOptional = articleRepository.findArticleByName(article.getName());

        if(articleReferenceOptional.isPresent() || articleNameOptional.isPresent()){
            throw new IllegalStateException("Reference taken and Name taken");
        }

        articleRepository.save(article);
    }
}
