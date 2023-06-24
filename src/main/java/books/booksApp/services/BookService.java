package books.booksApp.services;

import books.booksApp.model.Book;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BookService {
    List<Book> getAllBooks();

    void saveBook(Book book);
    void deleteBookById(Long id);

    void updateBook(Book book, Long Id);
}
