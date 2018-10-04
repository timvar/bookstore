package hh.palvelinohjelmointi.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import hh.palvelinohjelmointi.bookstore.domain.Book;
import hh.palvelinohjelmointi.bookstore.domain.Category;
import hh.palvelinohjelmointi.bookstore.repository.BookRepository;
import hh.palvelinohjelmointi.bookstore.repository.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner insertData(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			categoryRepository.save(new Category("Dekkari"));
			categoryRepository.save(new Category("Luonto"));
			categoryRepository.save(new Category("Elämänkerta"));
			categoryRepository.save(new Category("Jännitys"));
			
			bookRepository.save(new Book("Raid ja armonlaukaus", "Harri Nykänen", 2018, "9789522894526", 14.90, categoryRepository.findByName("Dekkari").get(0)));
			bookRepository.save(new Book("Sienet - aloittelijan opas", "Teija Saarinen", 2014, "9789526606163", 3.90, categoryRepository.findByName("Luonto").get(0)));
			bookRepository.save(new Book("Tuntematon Kimi Räikkönen", "Kari Hotakainen", 2018, "9789522345677", 19.90, categoryRepository.findByName("Elämänkerta").get(0)));
			bookRepository.save(new Book("Inferno", "Dan Brown", 2013, "9789510402061", 9.90, categoryRepository.findByName("Jännitys").get(0)));
			
	
		};
	}
}
 