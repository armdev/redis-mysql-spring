package io.project.controller;

import io.project.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.project.repository.ArticleRepository;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/articles")
public class ArticleController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Cacheable(value = "articles")
    @GetMapping(path = "/find/all")
    public List<Article> findAll() {
        LOG.info("Getting all articles");
        return articleRepository.findAll();
    }

    @Cacheable(value = "articles", key = "#id")
    @GetMapping(path = "/article")
    public Article findById(@RequestParam Long id) throws InterruptedException {
        LOG.info("Getting article with ID {}.", id);
        Optional<Article> article = articleRepository.findById(id);
        Thread.sleep(3000);
        return article.get();
    }

    @CachePut(value = "articles", key = "#article.id")
    @PutMapping("/update")
    public Article put(@RequestBody Article article) {
        articleRepository.save(article);
        return article;
    }

    @CacheEvict(value = "articles", allEntries = true)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        LOG.info("deleting article with id {}", id);
        articleRepository.deleteById(id);
    }
}
