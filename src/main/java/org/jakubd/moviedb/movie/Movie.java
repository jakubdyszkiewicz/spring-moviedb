package org.jakubd.moviedb.movie;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "MOVIE")
@EqualsAndHashCode(of = "id")
class Movie {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "ID", columnDefinition = "char(36)")
    private String id = UUID.randomUUID().toString();

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 500, nullable = false)
    private String description;

    @Column(name = "WATCHED")
    private boolean watched;
}
