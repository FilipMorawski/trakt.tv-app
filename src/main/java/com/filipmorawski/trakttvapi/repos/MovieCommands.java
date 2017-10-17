package com.filipmorawski.trakttvapi.repos;

import java.util.ArrayList;

public class MovieCommands {
    private ArrayList<String> movieCommands;

    public MovieCommands() {
        movieCommands = new ArrayList<>();
        movieCommands.add("watchlist");
        movieCommands.add("history");
        movieCommands.add("collection");
        movieCommands.add("watched");
    }

    public ArrayList<String> getMovieCommands() {
        return movieCommands;
    }

}
