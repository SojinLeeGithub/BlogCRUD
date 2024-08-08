package me.sojinlee.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.sojinlee.springbootdeveloper.domain.Article;
import me.sojinlee.springbootdeveloper.dto.AddArticleRequest;
import me.sojinlee.springbootdeveloper.dto.ArticleResponse;
import me.sojinlee.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController    //HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러

public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);

    }
    @GetMapping("/api/articles")
     public ResponseEntity<List<ArticleResponse>> findAllArticles() {
            List<ArticleResponse> articles = blogService.findAll()
                    .stream()
                    .map(ArticleResponse::new)
                    .toList();

            return ResponseEntity.ok()
                    .body(articles);

    }
}