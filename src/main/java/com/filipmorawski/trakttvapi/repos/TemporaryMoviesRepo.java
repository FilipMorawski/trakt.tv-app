package com.filipmorawski.trakttvapi.repos;

import com.filipmorawski.trakttvapi.clients.TraktClient;
import com.filipmorawski.trakttvapi.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TemporaryMoviesRepo {
    @Autowired
    private TraktClient traktClient;

    public ArrayList<Movie> getMovies(String user, String command) {
        return traktClient.getUserData(user, command);
    }

    public ArrayList<Movie> getMovies(String command) {
        return traktClient.getMoviesData(command);
    }
}
