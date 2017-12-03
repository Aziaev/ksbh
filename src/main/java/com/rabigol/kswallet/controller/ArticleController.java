package com.rabigol.kswallet.controller;

import com.rabigol.kswallet.entity.Article;
import com.rabigol.kswallet.service.IArticleService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("user")
public class ArticleController {
    private IArticleService articleService;

    @GetMapping("article/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id) {
        Article article = articleService.getArticleById(id);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }

    @GetMapping("articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> list = articleService.getAllArticles();
        return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
    }

    @PostMapping("article")
    public ResponseEntity<Void> addArticles(@RequestBody Article article, UriComponentsBuilder builder) {
        boolean flag = articleService.addArticle(article);
        if (!flag) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("article")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }

    @DeleteMapping("article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Required
    public void setArticleService(IArticleService articleService) {
        this.articleService = articleService;
    }
}
