package me.growbook91.blogproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//created_at, updated_at 자동업데이트
@EnableJpaAuditing
@SpringBootApplication
public class BlogProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogProjectApplication.class, args);
	}

}
