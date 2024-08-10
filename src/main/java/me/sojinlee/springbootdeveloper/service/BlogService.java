package me.sojinlee.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.sojinlee.springbootdeveloper.domain.Article;
import me.sojinlee.springbootdeveloper.dto.AddArticleRequest;
import me.sojinlee.springbootdeveloper.dto.UpdateArticleRequest;
import me.sojinlee.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 하나 조회하는 코드
    // findById = 글 하나 조회하는 메서드 , JPA 에서 제공하는 메서드
    // findById 는 데이터베이스에 저장되어 있는 글의 ID를 이용해 글을 조회한다.
    // 아래 코드는 findById 메서드를 이용해 ID를 받아 엔티티를 조회하고 없으면 IllegalArgumentException 예외
    // 를 발생시킨다.
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    // 업데이트 코드
    // @Transactional = 매칭한 메서드를 하나의 트랜잭션으로 묶는 역할
    @Transactional // 트랜잭션 메서드
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
