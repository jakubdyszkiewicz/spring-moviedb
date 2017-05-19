package org.jakubd.moviedb.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
class MovieController {

    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MovieDto create(@Valid @RequestBody MovieDto dto) {
        return movieService.create(dto);
    }

    @PutMapping("/{id}")
    MovieDto update(@PathVariable("id") String id, @Valid @RequestBody MovieDto dto) {
        return movieService.update(id, dto);
    }

    @GetMapping
    List<MovieDto> findAll() {
        return movieService.findAll();
    }

    @GetMapping(params = "watched")
    List<MovieDto> findAll(@RequestParam("watched") boolean watched) {
        return movieService.findWatched(watched);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") String id) {
        movieService.delete(id);
    }
}
