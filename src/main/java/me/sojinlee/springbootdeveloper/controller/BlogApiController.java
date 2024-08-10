package me.sojinlee.springbootdeveloper.controller;


import lombok.RequiredArgsConstructor;
import me.sojinlee.springbootdeveloper.domain.Article;
import me.sojinlee.springbootdeveloper.dto.AddArticleRequest;
import me.sojinlee.springbootdeveloper.dto.ArticleResponse;
import me.sojinlee.springbootdeveloper.dto.UpdateArticleRequest;
import me.sojinlee.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }


    // /api/article GET 요청이  오면 글 전체를 조회하는 findAll() 메서드를 호출한 다음 응답용 객체인
    // ArticleResponse 로 파싱해 body에 담아 클라이언트에게 전송한다.

    @GetMapping("/api/articles")
    //<ArticleResponse> = dto
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return  ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    // URL 경로에서 값 추출
    // URL 에서 {id]에 해당하는 값이 id로 들어옴
    // @PathVariable 애너테이션은 URL에서 값을 가져오는 애너테이션 : 동작원리 = 받은 id 값을 blogService 의 findById()메서드로 넘어가
    // id 값에 해당하는 블로그 글을 찾는다. 글을 찾으면 해당 글의 정보를 body에 담아 웹 브라우저로 전송한다.
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return  ResponseEntity.ok()
                .build();
    }

    // /api/articles/{id} PUT 요청이 오면 Request Body 정보가 request 로 넘어온다. 그리고 다시 서비스 클래스의
    // update() 메서드에 id와 request를 넘겨준다.
    // 응답 값은 body에 담아서 전송한다.
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }
}