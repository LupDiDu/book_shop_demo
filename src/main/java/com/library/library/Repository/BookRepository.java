package com.library.library.Repository;

import com.library.library.Books.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Scope("prototype")
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT s FROM Book s WHERE s.id = ?1")
    Optional<Book> findBookById(Long id);

    @Query("SELECT s FROM Book s WHERE s.authorId = ?1")
    List<Book> findBooksByAuthorId(Long id);

}
