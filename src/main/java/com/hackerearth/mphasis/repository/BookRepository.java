package com.hackerearth.mphasis.repository;

import com.hackerearth.mphasis.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleLike(String term);

}
