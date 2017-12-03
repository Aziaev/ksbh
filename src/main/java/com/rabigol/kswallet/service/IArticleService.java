package com.rabigol.kswallet.service;

import com.rabigol.kswallet.entity.Article;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IArticleService {
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    List<Article> getAllArticles();

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    Article getArticleById(long articleId);

    @Secured({"ROLE_ADMIN"})
    boolean addArticle(Article article);

    @Secured({"ROLE_ADMIN"})
    void updateArticle(Article article);

    @Secured({"ROLE_ADMIN"})
    void deleteArticle(long articleId);
}
