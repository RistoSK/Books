package fi.haagahelia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.domain.Category;
import fi.haagahelia.domain.CategoryRepository;
import fi.haagahelia.domain.Book;
import fi.haagahelia.domain.BookRepository;



@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("saving a couple of books");
			
			crepository.save(new Category("Sci-fi"));
			crepository.save(new Category("Action and Adventure"));
			crepository.save(new Category("Drama"));
			
			repository.save(new Book("bookTitle1" , "bookAuthor1" , 1994 , 12345 , 20, crepository.findByCatname("Sci-fi").get(0)));
			repository.save(new Book("bookTitle2" , "bookAuthor2" , 2000 , 6789 , 10, crepository.findByCatname("Action and Adventure").get(0)));
			
			
			log.info("fetching all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
