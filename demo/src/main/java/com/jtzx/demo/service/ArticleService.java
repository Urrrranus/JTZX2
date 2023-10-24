package com.jtzx.demo.service;

import com.jtzx.demo.dto.Article;
import com.jtzx.demo.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService  {
    @Autowired
    private ArticleMapper articleMapper;
//    发布文章
    public Integer publish(Article article,String username) {
        article.setPublishTime(LocalDateTime.now());//设置发布时间
        article.setAuthor(username);//作者名是这位用户的用户名
        articleMapper.insert(article);// 插入新文章
        return article.getId();//返回id
    }
//    查询所有文章
    public List<Article> getAll() {
        return articleMapper.findall();
}
//查询单个文章
    public Article getArticle(int id){
        return articleMapper.findbyid(id);
    }
// 删除文章
    public void delArticle(int id){
        Article article = getArticle(id);
    if (article !=null){
        articleMapper.delete(id);
        System.out.println("artice deleted successfully!");
    }
    }
//修改文章
        public void altArticle (Article article,Integer id){
            article.setId(id);
            article.setPublishTime(LocalDateTime.now());
            articleMapper.upcontent(article);
        }
    }