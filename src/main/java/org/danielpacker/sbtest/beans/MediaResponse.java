package org.danielpacker.sbtest.beans;

import java.util.List;

public class MediaResponse {

    private List<Book> books;
    private List<Movie> movies;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MediaResponse(List<Book> books, List<Movie> movies) {
        this.books = books;
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MediaResponse{" +
                "books=" + books +
                ", movies=" + movies +
                '}';
    }
}
