package com.filipmorawski.trakttvapi.controllers;

import com.filipmorawski.trakttvapi.domain.Movie;
import com.filipmorawski.trakttvapi.repos.TemporaryMoviesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/trakt")
public class MainController {
    @Autowired
    private TemporaryMoviesRepo temporaryMoviesRepo;

    @RequestMapping(value = "/{user}/{command}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String displayUserData(
            @PathVariable(required = false) String user,
            @PathVariable String command,
            Model model
    ) {
        ArrayList<Movie> movies = temporaryMoviesRepo.getMovies(user, command);
        model.addAttribute("command", command);
        model.addAttribute("movies", movies);
        return "movies";
    }

    @RequestMapping(value = "/{command}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String displayUserData(
            @PathVariable String command,
            Model model
    ) {
        ArrayList<Movie> movies = temporaryMoviesRepo.getMovies("maslok", command);
        model.addAttribute("command", command);
        model.addAttribute("movies", movies);
        return "movies";
    }

    @RequestMapping(value = "/movies/{command}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String displayMoviesData(
            @PathVariable String command,
            Model model
    ) {
        ArrayList<Movie> movies = temporaryMoviesRepo.getMovies(command);
        model.addAttribute("command", command);
        model.addAttribute("movies", movies);
        return "movies";
    }

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String displayOwnUserData(
            @RequestParam(value = "user") String user,
            @RequestParam(value = "command") String command,
            Model model
    ) {
        ArrayList<Movie> movies = temporaryMoviesRepo.getMovies(user, command);
        model.addAttribute("command", command);
        model.addAttribute("movies", movies);
        return "movies";
    }
}
