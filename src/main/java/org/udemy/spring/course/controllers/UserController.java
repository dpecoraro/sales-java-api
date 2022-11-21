package org.udemy.spring.course.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.udemy.spring.course.domain.bo.User;
import org.udemy.spring.course.dto.CredentialsDTO;
import org.udemy.spring.course.dto.TokenDTO;
import org.udemy.spring.course.exceptions.InvalidPasswordException;
import org.udemy.spring.course.security.JwtService;
import org.udemy.spring.course.services.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody
                     @Valid
                     User user) {
        return userService.save(user);
    }

    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody CredentialsDTO dto) {
        try {
            User user = User.builder()
                    .login(dto.getLogin())
                    .password(dto.getPassword())
                    .build();
            UserDetails userDetails = userService.auth(user);

            var token = jwtService.generateToken(user);

            return new TokenDTO(userDetails.getUsername(), token);
        } catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
