package com.projects.ads.core;

import com.projects.ads.article.ArticleController;
import com.projects.ads.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoreController {

    private final ArticleService articleService;

    @Autowired
    public CoreController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    String indexController(Model model) {
        model.addAttribute("title", "ADS (Agricultural Distribution Store)");
        model.addAttribute("articles", articleService.getArticles());

        return "index";
    }
}
