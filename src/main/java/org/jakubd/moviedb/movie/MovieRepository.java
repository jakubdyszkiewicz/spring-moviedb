package org.jakubd.moviedb.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {

    Optional<Movie> findById(String id);
}
