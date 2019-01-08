package org.danielpacker.sbtest.service;

import org.danielpacker.sbtest.beans.MediaResponse;
import org.danielpacker.sbtest.daos.BooksDao;
import org.danielpacker.sbtest.daos.MoviesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

    @Autowired
    private MoviesDao mDao;

    @Autowired
    private BooksDao bDao;

    public MediaResponse getAllByTitle(String title) {
        return new MediaResponse(
                bDao.getBooksByTitle(title),
                mDao.getMoviesByTitle(title));
    }

    public MediaResponse getAllByAuthor(String author) {
        return new MediaResponse(
                bDao.getBooksByAuthor(author),
                null);
    }

    public MediaResponse getMoviesByTitle(String title) {
        return new MediaResponse(
                null,
                mDao.getMoviesByTitle(title));
    }
}
