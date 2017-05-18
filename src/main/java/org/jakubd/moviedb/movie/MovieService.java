package org.jakubd.moviedb.movie;

import lombok.RequiredArgsConstructor;
import org.jakubd.moviedb.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        user.setLogin("user");
        movie.setUser(user);
        movie = movieRepository.save(movie);
        return movieMapper.map(movie);
    }
}
