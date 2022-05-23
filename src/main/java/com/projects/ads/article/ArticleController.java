package com.projects.ads.article;

import com.projects.ads.counter.CounterService;
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
        abstract Object addArticle(Article article);

        @RequestMapping
        abstract Object updateArticle(Long articleId, Article article);

        @RequestMapping
        abstract Object deleteArticle(Long articleId);
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

        @RequestMapping("/{articleId}")
        public Article getOneArticle(@PathVariable Long articleId) {
            return articleService.getOneArticle(articleId);
        }

        @Override
        @PostMapping
        public String addArticle(@RequestBody Article article) {
            articleService.addArticle(article);

            return "Article (" + article + ") added successfully!";
        }

        @Override
        @PutMapping(path = "{articleId}")
        public String updateArticle(@PathVariable("articleId") Long articleId, @RequestBody Article article) {
            articleService.updateArticle(articleId, article);

            return "Article (" + article + ")  updated successfully!";
        }

        @Override
        @DeleteMapping(path = "{articleId}")
        public String deleteArticle(@PathVariable("articleId") Long articleId) {
            articleService.deleteArticle(articleId);

            return "Article  (" + articleId + ")  deleted successfully!";
        }
    }

    @Controller
    @RequestMapping(path = "v1/articles")
    static class ArticleHTMLController extends ArticleBaseController {

        private final ArticleService articleService;
        private final CounterService counterService;

        @Autowired
        public ArticleHTMLController(ArticleService articleService, CounterService counterService) {
            this.articleService = articleService;
            this.counterService = counterService;
        }

        @Override
        @GetMapping
        ModelAndView getArticles() {
            ModelAndView model = new ModelAndView("articles");

            model.addObject("title", "Articles");
            model.addObject("articles", articleService.getArticles());
            model.addObject("counter", counterService);

            model.addObject("article", new Article());

            return model;
        }

        @Override
        @PostMapping(path = "/add")
        public String addArticle(@ModelAttribute Article article) {
            articleService.addArticle(article);

            return "redirect:/v1/articles";
        }

        @Override
        @PutMapping(path = "/update/{articleId}")
        public ModelAndView updateArticle(@PathVariable("articleId") Long articleId, @ModelAttribute("article") Article article) {
            return new ModelAndView("articles");
        }

        @Override
        @DeleteMapping(path = "/delete/{articleId}")
        public ModelAndView deleteArticle(@PathVariable("articleId") Long articleId) {
            return new ModelAndView("articles");
        }
    }
}
