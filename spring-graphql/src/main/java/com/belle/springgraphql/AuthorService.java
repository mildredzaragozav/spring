package com.belle.springgraphql;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {
    private final List<Author> authors = Arrays.asList(
            new Author("author-1", "Joshua", "Bloch"),
            new Author("author-2", "Douglas", "Adams"),
            new Author("author-3", "Bill", "Bryson")
    );

    public Author getAuthorById(String id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
