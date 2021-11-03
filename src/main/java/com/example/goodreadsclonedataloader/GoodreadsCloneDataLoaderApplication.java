package com.example.goodreadsclonedataloader;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.example.goodreadsclonedataloader.author.AuthorRepository;

import com.example.goodreadsclonedataloader.author.Author;

import com.example.goodreadsclonedataloader.connection.DataStaxAstraProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
// @EnableCassandraRepositories(basePackages = "author")
public class GoodreadsCloneDataLoaderApplication {

    @Autowired 
    AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(GoodreadsCloneDataLoaderApplication.class, args);
	}

    //This method runs when the application starts 
    @PostConstruct
    public void start() {
        Author author = new Author();
        author.setId("id");
        author.setName("name");
        author.setPersonalName("personalName");
        authorRepository.save(author);
    }

	/**
     * This is necessary to have the Spring Boot app use the Astra secure bundle 
     * to connect to the database
     */
	@Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }

}
