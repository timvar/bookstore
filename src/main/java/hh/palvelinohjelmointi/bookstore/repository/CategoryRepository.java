package hh.palvelinohjelmointi.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hh.palvelinohjelmointi.bookstore.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findByName(String name);
	List<Category> findAll();

}
