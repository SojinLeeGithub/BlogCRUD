package me.sojinlee.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.sojinlee.springbootdeveloper.domain.Article;
import me.sojinlee.springbootdeveloper.dto.AddArticleRequest;
import me.sojinlee.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
