package com.rabigol.kswallet.dao;

import com.rabigol.kswallet.entity.Article;

import java.util.List;

public interface IArticleDao {
    List<Article> getAllArticles();
    Article getArticleById(long articleId);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(long articleId);
    boolean articleExists(String title, String category);
}
