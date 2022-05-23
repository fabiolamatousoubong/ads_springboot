package com.projects.ads.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    public Article getOneArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalStateException("Article with id" + articleId + "does not exist.")
        );
    }
    
    public void addArticle(Article article) {
        Optional<Article> articleReferenceOptional = articleRepository.findArticleByReference(article.getReference());
        Optional<Article> articleNameOptional = articleRepository.findArticleByName(article.getName());

        if(articleReferenceOptional.isPresent() || articleNameOptional.isPresent()){
            throw new IllegalStateException("Reference and Name taken");
        }

        articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) {
        boolean exists = articleRepository.existsById(articleId); // True or False

        if (!exists) {
            throw new IllegalStateException("Article with id" + articleId + "does not exist.");
        }

        articleRepository.deleteById(articleId);
    }

    @Transactional
    public void updateArticle(Long articleId, Article article) {
        Article articleObject = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalStateException("Article with id" + articleId + "does not exist.")
        );

        if (article != null) {
            if (article.getName() != null && article.getName().length() > 0 && !Objects.equals(article.getName(), articleObject.getName())) {
                Optional<Article> articleNameOptional = articleRepository.findArticleByName(article.getName());
                if (articleNameOptional.isPresent()) {
                    throw new IllegalStateException("Name taken");
                }
                articleObject.setName(article.getName());
            }

            if (article.getDescription() != null && article.getDescription().length() > 0 && !Objects.equals(article.getDescription(), articleObject.getDescription())) {
                articleObject.setDescription(article.getDescription());
            }

            if (article.getQuantity() != null && article.getQuantity() != 0 && !Objects.equals(article.getQuantity(), articleObject.getQuantity())) {
                articleObject.setQuantity(article.getQuantity());
            }

            if (article.getQuantity_min() != null && article.getQuantity_min() != 0 && !Objects.equals(article.getQuantity_min(), articleObject.getQuantity_min())) {
                articleObject.setQuantity_min(article.getQuantity_min());
            }
        }
    }
}
