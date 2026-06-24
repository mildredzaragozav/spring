package com.belle.springgraphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @QueryMapping
    public Book bookById(@Argument String id) {
        return bookService.getBookById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return authorService.getAuthorById(book.authorId());
    }
}
