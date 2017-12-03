package com.rabigol.kswallet.service;

import com.rabigol.kswallet.dao.IArticleDao;
import com.rabigol.kswallet.entity.Article;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService {
    private IArticleDao articleDao;

    @Override
    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    @Override
    public Article getArticleById(long articleId) {
        return articleDao.getArticleById(articleId);
    }

    @Override
    public synchronized boolean addArticle(Article article) {
        if (articleDao.articleExists(article.getTitle(), article.getCategory())) {
            return false;
        } else {
            articleDao.addArticle(article);
            return true;
        }
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public void deleteArticle(long articleId) {
        articleDao.deleteArticle(articleId);
    }

    @Required
    public void setArticleDao(IArticleDao articleDao) {
        this.articleDao = articleDao;
    }
}
