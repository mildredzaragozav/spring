package com.belle.springgraphql;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = Arrays.asList(
            new Book("book-1", "Effective Java", 416, "author-1"),
            new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            new Book("book-3", "Down Under", 436, "author-3")
    );

    public Book getBookById(String id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
