package com.projects.ads.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


public class ArticleController {

    @RequestMapping(path = "articles")
    abstract static class ArticleBaseController {

        @RequestMapping
        abstract Object getArticles();

        @RequestMapping
        abstract void addArticle(Article article);

        @RequestMapping
        abstract void updateArticle(Long articleId, Article article);

        @RequestMapping
        abstract void deleteArticle(Long articleId);
    }

    @RestController
    @RequestMapping(path = "api/v1/articles")
    static class ArticleRESTController extends ArticleBaseController {

        private final ArticleService articleService;

        @Autowired
        public ArticleRESTController(ArticleService articleService) {
            this.articleService = articleService;
        }

        @Override
        @GetMapping
        public List<Article> getArticles() {
            return articleService.getArticles();
        }

        @Override
        @PostMapping
        public void addArticle(@RequestBody Article article) {
            articleService.addArticle(article);
        }

        @Override
        @PutMapping(path = "{articleId}")
        public void updateArticle(@PathVariable("articleId") Long articleId, @RequestBody Article article) {
            articleService.updateArticle(articleId, article);
        }

        @Override
        @DeleteMapping(path = "{articleId}")
        public void deleteArticle(@PathVariable("articleId") Long articleId) {
            articleService.deleteArticle(articleId);
        }
    }

    @Controller
    @RequestMapping(path = "v1/articles")
    static class ArticleHTMLController extends ArticleBaseController {

        private final ArticleService articleService;

        @Autowired
        public ArticleHTMLController(ArticleService articleService) {
            this.articleService = articleService;
        }

        @Override
        @GetMapping
        ModelAndView getArticles() {
            ModelAndView model = new ModelAndView("articles");

            model.addObject("title", "Articles");
            model.addObject("articles", articleService.getArticles());

            return model;
        }

        @Override
        @PostMapping
        public void addArticle(Article article) {
            // TODO: Add instruction
        }

        @Override
        @PutMapping
        public void updateArticle(Long articleId, Article article) {
            // TODO: Add instruction
        }

        @Override
        @DeleteMapping
        public void deleteArticle(Long articleId) {
            // TODO: Add instruction
        }
    }
}
