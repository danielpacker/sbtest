package org.danielpacker.sbtest.daos;

import org.danielpacker.sbtest.beans.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class BooksDao {

    private static final Map<String, String> data;

    static {
        data = new HashMap<String, String>();
        data.put("Ender's Game", "Orson Scott Card");
        data.put("Hitchhicker's Guide to the Galaxy", "Douglas Adams");
        data.put("I, Robot", "Issac Asimov");
    }

    public List<Book> getBooksByAuthor(String author) {
        Pattern pattern = Pattern.compile(".*" + author + ".*");
        return data.entrySet().stream()
                .filter( e -> e.getValue().matches(pattern.pattern()))
                .map(e -> new Book(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }


    public List<Book> getBooksByTitle(String title) {
        Pattern pattern = Pattern.compile(".*" + title + ".*");
        return data.entrySet().stream()
                .filter( e -> e.getKey().matches(pattern.pattern()))
                .map(e -> new Book(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
