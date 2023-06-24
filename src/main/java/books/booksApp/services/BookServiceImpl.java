package books.booksApp.services;

import books.booksApp.model.Book;
import books.booksApp.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.FetchNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

@Transactional
@Service

public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override

    public void deleteBookById(Long id) {
        try {
            Book book = bookRepository.findById(id).orElse(null);

            if (book != null) {
                bookRepository.delete(book);
                System.out.println("Deleted Book!!!");
            } else {
                throw new FetchNotFoundException("Delete Failed!! - Id does not exist", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Failed to delete!!!");
            e.printStackTrace();
            throw new FetchNotFoundException("Failed to delete the book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateBook(Book book, Long id) {

        try {
            Book updateBook = bookRepository.findById(id).orElse(null);

            if (updateBook != null) {
                updateBook.setAuthor(book.getAuthor());
                updateBook.setPrice(book.getPrice());
                updateBook.setTitle(book.getTitle());
                bookRepository.save(updateBook);
                System.out.println("Book Updated!!!");
            } else {
                throw new FetchNotFoundException("Update Failed!! - Id does not exist", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Failed to Update!!!");
            e.printStackTrace();


        }
    }
}




