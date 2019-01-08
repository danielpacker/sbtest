package org.danielpacker.sbtest.daos;

import org.danielpacker.sbtest.beans.Movie;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class MoviesDao {

    private static final Map<String, Integer> data;

    static {
        data = new HashMap<String, Integer>();
        data.put("Alien", 2000);
        data.put("Hitchhicker's Guide to the Galaxy", 2005);
        data.put("I, Robot", 2010);
    }

    public List<Movie> getMoviesByTitle(String title) {
        Pattern pattern = Pattern.compile(".*" + title + ".*");

        return data.entrySet().stream()
                .filter( e -> e.getKey().matches(pattern.pattern()))
                .map(e -> new Movie(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

}
