package com.tss.music_store_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages={"com.tss"})
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "com.tss.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.tss.entities")
public class MusicStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicStoreApplication.class, args);
	}

}
