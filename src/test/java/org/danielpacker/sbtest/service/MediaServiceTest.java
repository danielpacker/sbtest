package org.danielpacker.sbtest.service;

import org.danielpacker.sbtest.beans.Book;
import org.danielpacker.sbtest.beans.MediaResponse;
import org.danielpacker.sbtest.beans.Movie;
import org.danielpacker.sbtest.daos.BooksDao;
import org.danielpacker.sbtest.daos.MoviesDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {

    @Mock
    BooksDao bDao;

    @Mock
    MoviesDao mDao;

    @InjectMocks
    MediaService ms;

    List<Book> expectedBooks = new ArrayList<>();
    List<Movie> expectedMovies = new ArrayList<>();

    @Before
    public void setUp() {
        expectedBooks.add(new Book("Harry Potter","J.K. Rowling"));
        expectedBooks.add(new Book("Harry Potter and the Goblet of Fire","J.K. Rowling"));
        expectedMovies.add(new Movie("Harry Potter and the Goblet of Fire",2012));
        expectedMovies.add(new Movie("Attack of the Killer Tomatoes",1999));
        expectedMovies.add(new Movie("The Stuff",1988));
    }

    @Test
    public void getBooksByTitle_empty() {

        when(bDao.getBooksByTitle("")).thenReturn(expectedBooks);
        MediaResponse result = ms.getAllByTitle("");
        verify(bDao, times(1)).getBooksByTitle("");
        assertEquals(expectedBooks, result.getBooks());
    }

    @Test
    public void getAllByTitle_notEmpty() {

        final String title = "Potter";
        when(bDao.getBooksByTitle(title)).thenReturn(expectedBooks);
        MediaResponse result = ms.getAllByTitle(title);
        verify(bDao, times(1)).getBooksByTitle(title);
        assertEquals(expectedBooks, result.getBooks());
    }

    @Test
    public void getAllByAuthor_notEmptyMultiple() {

        final String author = "Rowling";
        when(bDao.getBooksByAuthor(author)).thenReturn(expectedBooks);
        MediaResponse result = ms.getAllByAuthor(author);
        verify(bDao, times(1)).getBooksByAuthor(author);
        assertEquals(expectedBooks, result.getBooks());
    }

    @Test
    public void getAllByTitle_moviesAndBooks() {

        final String title = "Potter";
        List<Book> filteredBooks = expectedBooks.stream()
                .filter(b -> b.getTitle().contains(title)).collect(Collectors.toList());
        List<Movie> filteredMovies = expectedMovies.stream()
                .filter(m -> m.getTitle().contains(title)).collect(Collectors.toList());

        when(bDao.getBooksByTitle(title)).thenReturn(filteredBooks);
        when(mDao.getMoviesByTitle(title)).thenReturn(filteredMovies);
        MediaResponse result = ms.getAllByTitle(title);
        verify(bDao, times(1)).getBooksByTitle(title);
        verify(mDao, times(1)).getMoviesByTitle(title);
        assertEquals(filteredBooks.size(), 2);
        assertEquals(filteredMovies.size(), 1);
        assertEquals(filteredBooks, result.getBooks());
        assertEquals(filteredMovies, result.getMovies());
    }
}
