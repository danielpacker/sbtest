package org.danielpacker.sbtest.controllers;

import org.danielpacker.sbtest.beans.MediaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.danielpacker.sbtest.service.MediaService;

@RestController
public class MediaController {

    private static final String API = "/media";

    @Autowired
    private MediaService ms;

    @RequestMapping("/")
    String home() {
        return "Visit /media, /media/books, or /media/movies";
    }

    @GetMapping(API)
    public MediaResponse media(@RequestParam(value="title", defaultValue="") String title) {
        return ms.getAllByTitle(title);
    }

    @GetMapping(API + "/books")
    public MediaResponse allbooks() {
        return ms.getAllByAuthor("");
    }

    @GetMapping(API + "/books" + "/author/{author}")
    public MediaResponse booksByAuthor(@PathVariable String author) {
        System.out.println("author: "+ author);
        return ms.getAllByAuthor(author);
    }

    @GetMapping(API + "/movies")
    public MediaResponse allMovies() {
        return ms.getMoviesByTitle("");
    }

    @GetMapping(API + "/movies" + "/title/{title}")
    public MediaResponse moviesByTiitle(@PathVariable String title) {
        return ms.getMoviesByTitle(title);
    }
}
