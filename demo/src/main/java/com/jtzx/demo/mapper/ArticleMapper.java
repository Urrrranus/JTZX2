package com.jtzx.demo.mapper;

import com.jtzx.demo.dto.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //通过id查询某篇文章
   @Select("""
           select id,
                  title,
                  content,
                  author,
                  publish_time
           from article
           where id = #{id}
           """)
   Article findbyid(Integer id);
   //查询所有文章
   @Select("""
           select id,
                  title,
                  content,
                  author,
                  publish_time
           from article
           """)
  List<Article> findall();
   //修改文章
   @Update("""
           update article set content = #{content},title = #{title}
           where id = #{id}
           """)
   void upcontent(Article article);
   //删除文章
   @Delete("""
           delete from article where id = #{id}
           """)
   void  delete(Integer id);
   //插入新文章
   @Insert("""
           insert into article(title,content,author,publish_ime)
           values( #{title}, #{content}, #{author}, #{publishTime})
           """)
   void insert(Article article);

}
