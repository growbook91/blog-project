package me.growbook91.blogproject.controller;

import lombok.RequiredArgsConstructor;
import me.growbook91.blogproject.domain.Article;
import me.growbook91.blogproject.dto.AddArticleRequest;
import me.growbook91.blogproject.dto.ArticleResponse;
import me.growbook91.blogproject.dto.ArticleViewResponse;
import me.growbook91.blogproject.dto.UpdateArticleRequest;
import me.growbook91.blogproject.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllAritcles(){
        //stream은 뭘까
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
            Article article = blogService.findById(id);

            // 이 친구의 역할은 뭘까..?
            // 얘가 데이터의 전송을 담당하는 것 같다.
            return ResponseEntity.ok()
                    .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }


}
