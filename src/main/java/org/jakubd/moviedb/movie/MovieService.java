package org.jakubd.moviedb.movie;

import lombok.RequiredArgsConstructor;
import org.jakubd.moviedb.system.exception.NotFoundException;
import org.jakubd.moviedb.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Transactional
    public MovieDto create(MovieDto movieDto) {
        Movie movie = movieMapper.map(movieDto);
        User user = new User();
        user.setLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        movie.setUser(user);
        movie = movieRepository.save(movie);
        return movieMapper.map(movie);
    }

    public List<MovieDto> findAll() {
        return movieRepository.findAll().stream()
                .map(movieMapper::map)
                .collect(toList());
    }

    public List<MovieDto> findWatched(boolean watched) {
        return movieRepository.findByWatched(watched).stream()
                .map(movieMapper::map)
                .collect(toList());
    }

    @Transactional
    public MovieDto update(String id, MovieDto dto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setWatched(dto.isWatched());
        return movieMapper.map(movie);
    }

    @Transactional
    public void delete(String id) {
        movieRepository.delete(id);
    }
}
