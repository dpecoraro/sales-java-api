package org.udemy.spring.course.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "login")
    @NotEmpty(message = "Username must be filled")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "paassword must be filled")
    private String password;

    private boolean admin;
}
