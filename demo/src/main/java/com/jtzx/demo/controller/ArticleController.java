package com.jtzx.demo.controller;

import com.jtzx.demo.dto.Article;
import com.jtzx.demo.service.ArticleService;
import com.jtzx.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {
    //查询单个文章
   @RequestMapping("/article/{id}")
   @ResponseBody
    public Object e(@PathVariable Integer id,@RequestHeader("token") String token ) {
       userService.checktoken(token);
       return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.OK);
   }
    //查询所有文章
    @RequestMapping("/all")
    @ResponseBody
    public Object u(@RequestHeader("token") String token ) {
       userService.checktoken(token);
       return new ResponseEntity<>(articleService.getAll(), HttpStatus.OK);
    }
   //发布新文章
    @RequestMapping("/pubish")
    @ResponseBody
    public Object pubish(@RequestBody Article article,@RequestHeader("token") String token) {
        userService.checktoken(token);
        String username = userService.search(token);
        return new ResponseEntity<>(articleService.publish(article,username),HttpStatus.OK);
    }
   //修改文章
    @RequestMapping("/alter")
    @ResponseBody
    public Object alter(@RequestBody Article article,@PathVariable Integer id,@RequestHeader("token") String token) {
       Article article1 = articleService.getArticle(id);
       String auther = article1.getAuthor();
       Boolean is = userService.authentication(token,auther);
          if (is ) {
            articleService.altArticle(article, id);
          return "Updated Successfully!";
       }else {
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
          }
    }

   //删除文章
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@PathVariable Integer id,@RequestHeader("token") String token) {
        Article article1 = articleService.getArticle(id);
        String auther = article1.getAuthor();
        Boolean is = userService.authentication(token, auther);
        if (is) {
            articleService.delArticle(id);
            return "Deleted Successfully!";
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
}
