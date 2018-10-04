package hh.palvelinohjelmointi.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import hh.palvelinohjelmointi.bookstore.domain.Book;
import hh.palvelinohjelmointi.bookstore.repository.BookRepository;
import hh.palvelinohjelmointi.bookstore.repository.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	// Login page for Bookstore
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Home page
	@GetMapping(value="/index")
	public String showHomePage(){
		
		return "home";
	}
	
	// show all books
	@GetMapping(value="/booklist")
	public String showBookListPage(Model model) {
		
		model.addAttribute("books", bookRepository.findAll());
		
		return "booklist";
	}
	
	// show all books REST
	@GetMapping(value="/books")
	public @ResponseBody List<Book> showBookListRest(Model model) {
		return (List<Book>) bookRepository.findAll();
	}
	
	// get book by id REST
    @GetMapping(value="/books/{id}")
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }     
	
	
	// add book
	@GetMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }   
	
	//add and save new book
	@PostMapping(value = "/save")
    public String save(Book book){
		bookRepository.save(book);		
        return "redirect:booklist";
    }
	
	//delete book
	@GetMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookRepository.deleteById(bookId);
        return "redirect:../booklist";
    }
	
	// edit book
	@GetMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
		
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
    }
	
	// edit and update book
	@PostMapping(value = "/update")
    public String updateBook(Book book){
		bookRepository.save(book);
        return "redirect:booklist";
    }
	
}
