package awanmr.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import awanmr.restful.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
