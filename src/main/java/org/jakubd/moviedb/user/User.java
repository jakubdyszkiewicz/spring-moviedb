package org.jakubd.moviedb.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "LOGIN", length = 32)
    private String login;

    @Column(name = "PASSWORD", columnDefinition = "char(64)")
    private String password;
}
