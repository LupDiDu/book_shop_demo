package com.library.library.Repository;

import com.library.library.Books.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("from Author a where a.name like %:name%")
    List<Author> findAuthorByName(@Param("name") String name);

    @Query("from Author a where a.id = :id")
    Author findAuthorById(@Param("id") Long id);
}
