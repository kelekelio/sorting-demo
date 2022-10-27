package com.grzegorznowakowski.sortingdemo;

import com.grzegorznowakowski.sortingdemo.item.entity.Item;
import com.grzegorznowakowski.sortingdemo.item.repository.ItemRepository;
import com.grzegorznowakowski.sortingdemo.translation.entity.Translation;
import com.grzegorznowakowski.sortingdemo.translation.repository.TranslationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SortingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SortingDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleData(ItemRepository repository, TranslationRepository translationRepository) {
		return (args) -> {
			Translation tr1 = translationRepository.save(new Translation(1L, "translation_1", "some text 1"));
			Translation tr2 = translationRepository.save(new Translation(2L, "translation_2", "some text 2"));

			repository.save(new Item(1L, "test_item_1", tr1));
			repository.save(new Item(2L, "test_item_2", tr2));
		};
	}
}
