package awanmr.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import awanmr.restful.model.CreateMovieRequest;
import awanmr.restful.model.MovieResponse;
import awanmr.restful.model.UpdateMovieRequest;
import awanmr.restful.model.WebResponse;
import awanmr.restful.service.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(
            path = "/movies",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<MovieResponse>> register() {
        List<MovieResponse> allMovie = movieService.allMovie();
        return WebResponse.<List<MovieResponse>>builder().data(allMovie).build();
    }

    @GetMapping(
            path = "/movies/{movieId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MovieResponse> get(@PathVariable("movieId") Long movieId) {
        MovieResponse movieResponse = movieService.getById(movieId);
        return WebResponse.<MovieResponse>builder().data(movieResponse).build();
    }

    @PostMapping(
            path = "/movies",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MovieResponse> create(@RequestBody CreateMovieRequest request) {
        MovieResponse movieResponse = movieService.create(request);
        return WebResponse.<MovieResponse>builder().data(movieResponse).build();
    }

    @PatchMapping(
            path = "/movies/{movieId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MovieResponse> update(@RequestBody UpdateMovieRequest request,
    @PathVariable("movieId") Long movieId) {
        request.setMovieId(movieId);
        MovieResponse movieResponse = movieService.update(request);
        return WebResponse.<MovieResponse>builder().data(movieResponse).build();
    }

    @DeleteMapping(
            path = "/movies/{movieId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("movieId") Long movieId) {
        movieService.delete(movieId);
        return WebResponse.<String>builder().data("Delete Movie Success").build();
    }
}
