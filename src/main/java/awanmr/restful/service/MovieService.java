package awanmr.restful.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import awanmr.restful.entity.Movie;
import awanmr.restful.model.UpdateMovieRequest;
import awanmr.restful.model.CreateMovieRequest;
import awanmr.restful.model.MovieResponse;
import awanmr.restful.repository.MovieRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ValidationService validationService;

    public List<MovieResponse> allMovie() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse> moviesResponse = new ArrayList<>();
        for (Movie movie : movies) {
            MovieResponse movieResponse = build(movie);
            moviesResponse.add(movieResponse);
        }
        return moviesResponse;
    }

    @Transactional
    public MovieResponse create(CreateMovieRequest request) {
        validationService.validate(request);

        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setRating(request.getRating());
        movie.setImage("");
        movie.setCreatedAt(LocalDateTime.now());
        Movie save = movieRepository.save(movie);

        return build(save);
    }

    public MovieResponse getById(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if(movie != null){
            return build(movie);
        }
        return null;
    }

    public void delete(Long id) {
        Movie movie = movieRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Delete Gagal, Movie not found"));
        if(movie != null){
            movieRepository.deleteById(id);
        }
    }

    @Transactional
    public MovieResponse update(UpdateMovieRequest request) {
        validationService.validate(request);

        log.info("REQUEST : {}", request);

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update Gagal, Movie not found"));

        if (Objects.nonNull(request.getTitle())) {
            movie.setTitle(request.getTitle());
        }

        if (Objects.nonNull(request.getDescription())) {
            movie.setDescription(request.getDescription());
        }

        if (Objects.nonNull(request.getRating())) {
            movie.setRating(request.getRating());
        }

        if (Objects.nonNull(request.getImage())) {
            movie.setImage(request.getImage());
        }

        movie.setUpdatedAt(LocalDateTime.now());

        Movie save = movieRepository.save(movie);

        return build(save);
    }

    private MovieResponse build(Movie movie){
        return MovieResponse.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .description(movie.getDescription())
                    .rating(movie.getRating())
                    .image(movie.getImage())
                    .createdAt(movie.getCreatedAt())
                    .updatedAt(movie.getUpdatedAt()).build();
    }
}
