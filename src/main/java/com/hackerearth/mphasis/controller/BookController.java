package com.hackerearth.mphasis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerearth.mphasis.dao.Book;
import com.hackerearth.mphasis.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    private static final Logger LOGGER= LogManager.getLogger(BookController.class);

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks(){
        LOGGER.info("Fetching all books");
        List<Book> books = bookRepository.findAll();
        LOGGER.info("Completed fetching all books");
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookWithId(@PathVariable long bookId){
        LOGGER.info("Fetching book with bookId: " + bookId);
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty())
            LOGGER.info("No book found with bookId: " + bookId);
        else
            LOGGER.info("Book details fetched: " + book.get());
        return book.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.of(book);
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<List<Book>> getBooksWithTerm(@PathVariable String term){
        term = "%" + term + "%";
        LOGGER.info("Searching for book with term: " + term);
        List<Book> books = bookRepository.findByTitleLike(term);
        LOGGER.info("Books fetched: " + books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/dumpData")
    public ResponseEntity dumpDataInDB(@Value("${datasource}") String source, @Value("${shouldDataLoadHappen}") boolean shouldDataLoadHappen) throws IOException {
        if(shouldDataLoadHappen){
            ObjectMapper objectMapper = new ObjectMapper();
            Book[] books = objectMapper.readValue(new URL(source), Book[].class);
            Arrays.stream(books).forEach(bookRepository::save);
            return ResponseEntity.ok("Data Load Complete");
        }
        else{
            return ResponseEntity.ok("Data Load Already Complete");
        }
    }
}
