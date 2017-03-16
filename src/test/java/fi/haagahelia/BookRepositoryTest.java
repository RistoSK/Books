package fi.haagahelia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.domain.Category;
import fi.haagahelia.domain.Book;
import fi.haagahelia.domain.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void findByAuthorShouldReturnBook() {
        List<Book> books = repository.findByAuthor("bookAuthor1");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("bookTitle1");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("bookTitle3", "bookAuthor3", 2012 , 23 , 30 , new Category("Horror"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    

}