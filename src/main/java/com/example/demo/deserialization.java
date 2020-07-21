package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//The Demo used a JSON file sent via PostMan.  Raw - JSON

@RestController
public class deserialization {
   static class Article{
       private String title;
       private String createBy;
       public String getTitle() { return title; }
       public void setTitle(String title) { this.title = title; }
       public String getCreateBy() { return createBy; }
       public void setCreateBy(String createBy) { this.createBy = createBy; }
   }

    static class Blog {
        private String Title;
        private Article[] articles;

        public String getTitle() { return Title; }
        public void setTitle(String title) { Title = title; }
        public Article[] getArticles() { return articles; }
        public void setArticles(Article[] articles) { this.articles = articles; }
    }
    @PostMapping("/nested-example")
        public String getNested(@RequestBody Blog blog) {
        return blog.articles[0].getTitle();
    }
}
