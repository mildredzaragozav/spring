package com.belle.springgraphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.graphql.test.autoconfigure.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;

@GraphQlTest(BookController.class) // Useful annotation for writing controller slice tests
public class BookControllerTests {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private BookService bookService;
    @MockitoBean
    private AuthorService authorService;

    @Test
    void shouldGetFirstBook() {
        Book mockBook = new Book("book-1", "Effective Java", 416,"author-1");
        Author mockAuthor =  new Author("author-1", "Joshua", "Bloch");

        when(bookService.getBookById("book-1")).thenReturn(mockBook);
        when(authorService.getAuthorById("author-1")).thenReturn(mockAuthor);

        this.graphQlTester
                .documentName("bookDetails")
                .variable("id", "book-1")
                .execute()
                .path("bookById")
                .matchesJson("""
                    {
                        "id": "book-1",
                        "name": "Effective Java",
                        "pageCount": 416,
                        "author": {
                          "firstName": "Joshua",
                          "lastName": "Bloch"
                        }
                    }
                """);

    }

}
