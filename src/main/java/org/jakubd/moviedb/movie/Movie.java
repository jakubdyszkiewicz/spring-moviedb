package org.jakubd.moviedb.movie;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.jakubd.moviedb.user.User;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "MOVIE")
@EqualsAndHashCode(of = "id")
class Movie {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "ID", columnDefinition = "char(36)")
    private String id;

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 500, nullable = false)
    private String description;

    @Column(name = "WATCHED")
    private boolean watched;

    @ManyToOne
    @JoinColumn(name = "USER_LOGIN")
    private User user;

    Movie() {
        this.id = UUID.randomUUID().toString();
    }

    Movie(String id) {
        this.id = id;
    }
}
