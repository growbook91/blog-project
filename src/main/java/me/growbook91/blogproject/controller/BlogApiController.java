package me.growbook91.blogproject.controller;

import lombok.RequiredArgsConstructor;
import me.growbook91.blogproject.domain.Article;
import me.growbook91.blogproject.dto.AddArticleRequest;
import me.growbook91.blogproject.dto.ArticleResponse;
import me.growbook91.blogproject.dto.UpdateArticleRequest;
import me.growbook91.blogproject.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    // 여기서 볼 수 있는 것처럼 response나 request를 요청할 때는 그냥 하지 않고 DTO라는 class를 만들어서 그걸 통해 data를 주고 받는 구나.
    public ResponseEntity<List<ArticleResponse>> findAllAritcles(){
        List<ArticleResponse> articles = blogService.findAll()
                // 오...이걸 통해서 list가 변경되네..
                .stream()
                // 여기서 어떤 함수를 사용해서 바꿀 건지를 지정하는 듯...
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
            Article article = blogService.findById(id);

            // 이 친구의 역할은 뭘까..?
            // 이 친구는 HTTP 응답의 body로 쓸 내용.
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
