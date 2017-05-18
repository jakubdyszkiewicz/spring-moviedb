package org.jakubd.moviedb.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {

    Optional<Movie> findById(String id);

    List<Movie> findByWatched(boolean watched);
}
