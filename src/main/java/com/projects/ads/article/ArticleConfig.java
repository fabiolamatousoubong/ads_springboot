package com.projects.ads.article;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ArticleConfig {

    @Bean
    CommandLineRunner commandLineRunner(ArticleRepository articleRepository) {
        return args -> {
            Article redTomato = new Article(
                    "8c1fca0a427b4536a22e1d555baebaf0",
                    "Red Tomato",
                    "A red vegetable.",
                    50,
                    10
            );

            Article greenTomato =new Article(
                    "Green Tomato",
                    "A green vegetable.",
                    40,
                    10
            );

            Article yellowTomato = new Article(
                    "Yellow Tomato",
                    "A yellow vegetable.",
                    30,
                    10
            );

            articleRepository.saveAll(
                    List.of(redTomato, greenTomato, yellowTomato)
            );
        };
    }
}
