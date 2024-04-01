package me.growbook91.blogproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 원래는 autoconfig 만들어서 거기에 @configuration이랑 @componentscan을 너ㅎ어줬는데,
// @SpringBootApplication 안에 다 포함되어 있다.
// 아...그래서 김영한님 강의 때, 얘를 포함하면 자동으로 다른 것들이 포함돼서 에러가 났던 것 같다. 아하...
@SpringBootApplication
public class BlogProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogProjectApplication.class, args);
	}

}
