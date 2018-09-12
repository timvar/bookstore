package hh.palvelinohjelmointi.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.palvelinohjelmointi.bookstore.domain.Book;
import hh.palvelinohjelmointi.bookstore.repository.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookrepository;
	
	@GetMapping(value="/index")
	public String showHomePage(){
		
		return "home";
	}
	
	@GetMapping(value="/booklist")
	public String showBookListPage(Model model) {
		
		model.addAttribute("books", bookrepository.findAll());
		
		return "booklist";
	}
		
	@GetMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        return "addbook";
    }   
	
	@PostMapping(value = "/save")
    public String save(Book book){
		bookrepository.save(book);
        return "redirect:booklist";
    }
	
	@GetMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookrepository.deleteById(bookId);
        return "redirect:../booklist";
    }
	
	@GetMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
		
		model.addAttribute("book", bookrepository.findById(bookId));
		return "editbook";
    }
	
	@PostMapping(value = "/update")
    public String updateBook(Book book){
		bookrepository.save(book);
        return "redirect:booklist";
    }
	
}
