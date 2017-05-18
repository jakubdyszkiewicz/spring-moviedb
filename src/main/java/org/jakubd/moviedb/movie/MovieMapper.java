package org.jakubd.moviedb.movie;

import org.springframework.stereotype.Component;

@Component
class MovieMapper {

    MovieDto map(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setWatched(movie.isWatched());
        return dto;
    }

    Movie map(MovieDto dto) {
        Movie movie;
        if (dto.getId() != null) {
            movie = new Movie(dto.getId());
        } else {
            movie = new Movie();
        }
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setWatched(dto.isWatched());
        return movie;
    }
}
