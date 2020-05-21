package com.testSpring.Blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.testSpring.Blog.controllers.gitUtils.FileSystemUtils.loadHistorySettings;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		loadHistorySettings();
	}

}
