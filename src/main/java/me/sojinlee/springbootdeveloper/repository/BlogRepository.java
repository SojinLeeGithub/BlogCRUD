package me.sojinlee.springbootdeveloper.repository;


import me.sojinlee.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface BlogRepository extends JpaRepository<Article, Long> {

    }

