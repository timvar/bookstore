package hh.palvelinohjelmointi.bookstore;


import hh.palvelinohjelmointi.bookstore.domain.Book;
import hh.palvelinohjelmointi.bookstore.repository.BookRepository;
import hh.palvelinohjelmointi.bookstore.repository.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing BookRepository, CategoryRepository and UserRepository
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByAuthorShouldReturnBook() {
        List<Book> books = bookRepository.findByAuthor("Kari Hotakainen");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getYear()).isEqualTo(2018);
    }

    @Test
    public void createNewBook() {
        Book book = new Book("Kahvakuulakoulu", "Tuomo Kilpeläinen", 2013, "9789526618029", 18.20, categoryRepository.findByName("Liikunta").get(0));
        bookRepository.save((book));
        assertThat(book.getId()).isNotNull();

    }

    @Test
    public void deleteBook() {

        List<Book> books = bookRepository.findByAuthor("Harri Nykänen");
        assertThat(books).hasSize(1);
        bookRepository.deleteById(books.get(0).getId());
        books = bookRepository.findByAuthor("Harri Nykänen");
        assertThat(books).hasSize(0);


    }
}
