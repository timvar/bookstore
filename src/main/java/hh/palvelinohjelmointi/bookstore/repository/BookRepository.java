package hh.palvelinohjelmointi.bookstore.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import hh.palvelinohjelmointi.bookstore.domain.Book;

public interface BookRepository extends CrudRepository<Book,Long> {
	
	List<Book> findAll();
	
}
