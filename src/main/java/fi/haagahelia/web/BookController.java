package fi.haagahelia.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.domain.BookRepository;
import fi.haagahelia.domain.Book;
import fi.haagahelia.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/login")
   	public String login() {
   		return "login";
   	}
	
	@RequestMapping(value="/booklist")
    public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
       return "booklist";
	}
	
	// RESTful service to get all books
	@RequestMapping(value="/booksJson", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
	return (List<Book>) repository.findAll();
	}
	
	// RESTful service to get books by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findStudentRest(@PathVariable("id") Long id) {	
    	return repository.findOne(id);
    } 
    
    @PreAuthorize("hasAuthority('ADMIN')")
   	@RequestMapping(value = "/add")
    public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
       return "addbook";
   	}
   	
  //Save Book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }  
    
    //Delete Book
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.delete(bookId);
        return "redirect:../booklist";
    }       
}